package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.ArticleDto;
import com.yozuru.domain.dto.ArticleListDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.entity.Article;
import com.yozuru.domain.vo.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */
@Validated
public interface ArticleService extends IService<Article> {

    ResponseResult<List<HotArticlesVo>> hotArticlesList();

    ResponseResult<PageVo<ArticleListVo>> getArticleList(Long categoryId, PageDto pageDto);

    ResponseResult<ArticleDetailVo> getArticleDetail(Long id);

    ResponseResult<Object> updateViewCount(Long id);
    @Validated
    ResponseResult<Object> addArticle(ArticleDto articleDto);
    @Validated
    ResponseResult<PageVo<ArticleListVo>> getAdminArticleList(ArticleListDto articleListDto, PageDto pageDto);

    ResponseResult<ArticleDto> getAdminArticleDetail(Long id);

    @Validated
    ResponseResult<Object> updateArticle(ArticleDto articleDto);

    ResponseResult<Object> deleteArticle(Long id);
}

