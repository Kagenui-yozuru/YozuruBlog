package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.vo.HotArticlesVo;
import com.yozuru.mapper.ArticleMapper;
import com.yozuru.domain.entity.Article;
import com.yozuru.service.ArticleService;
import com.yozuru.utils.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public ResponseResult<List<HotArticlesVo>> hotArticlesList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        状态为已发布即状态码为0
        queryWrapper.eq(Article::getStatus, SystemConstant.ARTICLES_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        queryWrapper.last("limit "+SystemConstant.HOT_ARTICLES_LIST_SIZE);
        List<Article> list = list(queryWrapper);
        List<HotArticlesVo> hotArticlesVos = BeanCopyUtil.copyBeanList(list, HotArticlesVo.class);
        return ResponseResult.success(hotArticlesVos);
    }
}

