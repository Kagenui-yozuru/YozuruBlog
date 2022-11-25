package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Article;
import com.yozuru.domain.vo.ArticleDetailVo;
import com.yozuru.domain.vo.ArticleListVo;
import com.yozuru.domain.vo.HotArticlesVo;
import com.yozuru.domain.vo.PageVo;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */

public interface ArticleService extends IService<Article> {

    ResponseResult<List<HotArticlesVo>> hotArticlesList();

    ResponseResult<PageVo<ArticleListVo>> getArticleList(Long categoryId, Integer pageNum, Integer pageSize);

    ResponseResult<ArticleDetailVo> getArticleDetail(Long id);

    ResponseResult<Object> updateViewCount(Long id);
}

