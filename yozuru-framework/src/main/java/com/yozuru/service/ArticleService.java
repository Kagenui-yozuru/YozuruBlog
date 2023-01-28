package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.backstage.ArticleDto;
import com.yozuru.domain.dto.backstage.QueryArticleListDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.entity.Article;
import com.yozuru.domain.vo.*;
import com.yozuru.domain.vo.forestage.ArticleDetailVo;
import com.yozuru.domain.vo.forestage.ArticleListVo;
import com.yozuru.domain.vo.forestage.HotArticlesVo;
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
    @Validated(ArticleDto.Add.class)
    ResponseResult<Object> addArticle(ArticleDto articleDto);
    @Validated
    ResponseResult<PageVo<ArticleListVo>> getAdminArticleList(QueryArticleListDto queryArticleListDto, PageDto pageDto);

    ResponseResult<ArticleDto> getAdminArticleDetail(Long id);

    @Validated(ArticleDto.Update.class)
    ResponseResult<Object> updateArticle(ArticleDto articleDto);

    boolean updateViewCountById(Long id,Long viewCount);

    ResponseResult<Object> deleteArticle(Long id);
}

