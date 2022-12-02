package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.AddCommentDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.entity.Comment;
import com.yozuru.domain.vo.CommentVo;
import com.yozuru.domain.vo.PageVo;

/**
 * 评论表(Comment)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-19 23:26:54
 */

public interface CommentService extends IService<Comment> {

    ResponseResult<PageVo<CommentVo>> getCommentList(Long articleId, PageDto pageDto);

    ResponseResult<Object> addCommentList(AddCommentDto addCommentDto);
}

