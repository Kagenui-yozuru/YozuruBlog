package com.yozuru.service.forestage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.dto.forestage.AddCommentDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.entity.User;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.forestage.CommentVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.mapper.CommentMapper;
import com.yozuru.domain.entity.Comment;
import com.yozuru.service.forestage.CommentService;
import com.yozuru.service.UserService;
import com.yozuru.utils.BeanCopyUtil;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-19 23:26:54
 */

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult<PageVo<CommentVo>> getCommentList(Long articleId, PageDto pageDto) {
        //查询文章对应的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //匹配文章id
        queryWrapper.eq(Comment::getArticleId,articleId)
                //匹配根评论
                .eq(Comment::getRootId, SystemConstant.COMMENT_ROOT_ID)
                .eq(Comment::getType,SystemConstant.COMMENT_TYPE_ARTICLE)
                .orderByDesc(Comment::getCreateTime);
        ;
        Page<Comment> pageObj = new Page<>(pageDto.getPageNum(),pageDto.getPageSize());
        //进行分页查询
        pageObj=page(pageObj,queryWrapper);
        //把查询的结果封装成List<CommentVo>
        List<CommentVo> rootCommentVoList = toCommentVoList(pageObj.getRecords());
        //再把根评论的子评论赋给每个元素
        List<CommentVo> commentVoList = rootCommentVoList.stream()
                //自己的id作为子评论的根id
                .peek(commentVo -> commentVo.setChildren(getChildrenComment(commentVo.getId())))
                .collect(Collectors.toList());
        //把List<CommentVo>封装成PageVo
        PageVo<CommentVo> commentPageVo = new PageVo<>(commentVoList, pageObj.getTotal());
        //再封装成Result
        return ResponseResult.success(commentPageVo);
    }

    @Override
    public ResponseResult<Object> addCommentList(AddCommentDto addCommentDto) {
        if (!Strings.hasText(addCommentDto.getContent())){
            throw new BusinessException(HttpCodeEnum.COMMENT_NOT_NULL);
        }
        Comment comment = BeanCopyUtil.copyBean(addCommentDto, Comment.class);
        //CreateBy字段由mybatis的自动填充来补充，获得自Authentication对象(或者说是token)。
        save(comment);
        return ResponseResult.success();
    }

    /**
     * 通过评论id,获得该评论的所有子评论
     * @param rootId 评论id
     * @return List<CommentVo>子评论列表
     */
    private List<CommentVo> getChildrenComment(Long rootId){
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,rootId);
        List<Comment> list = list(queryWrapper);
        return toCommentVoList(list);
    }

    /**
     * 把List<Comment>转换成List<CommentVo>,即把评论的用户信息补充上
     * @param commentList 评论列表
     * @return List<CommentVo> 评论视图列表
     */
    private List<CommentVo> toCommentVoList(List<Comment> commentList){
        //先通过工具类给CommentVo中公共属性复制
        List<CommentVo> commentVoList = BeanCopyUtil.copyBeanList(commentList, CommentVo.class);
        return commentVoList.stream()
                //使用peek方法对列表中每个元素重新复制
                .peek(commentVo -> {
                    LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
                    //只返回需要的字段，降低数据库传输压力
                    queryWrapper1.select(User::getNickName,User::getAvatar)
                            .eq(User::getId,commentVo.getCreateBy());
                    User commentUser = userService.getOne(queryWrapper1);
                    commentVo.setUsername(commentUser==null?SystemConstant.CLOSED_USERNAME:commentUser.getNickName());
                    commentVo.setAvatar(commentUser==null?"":commentUser.getAvatar());
                    //如果是根评论则不需要进行查询
                    if (commentVo.getRootId()!=-1) {
                        LambdaQueryWrapper<User> queryWrapper2 = new LambdaQueryWrapper<>();
                        queryWrapper2.select(User::getUserName,User::getNickName)
                                .eq(User::getId,commentVo.getToCommentUserId());
                        User toCommentUser = userService.getOne(queryWrapper2);
                        if (toCommentUser==null){
                            commentVo.setToCommentUserName(SystemConstant.CLOSED_USERNAME);
                        } else if (Strings.hasText(toCommentUser.getNickName()))
                            commentVo.setToCommentUserName(toCommentUser.getNickName());
                        else
                            commentVo.setToCommentUserName(toCommentUser.getUserName());
                    }
                })
                //转换为List
                .collect(Collectors.toList());
    }
}

