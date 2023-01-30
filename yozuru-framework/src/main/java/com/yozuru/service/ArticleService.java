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
public interface ArticleService extends IService<Article> {
    /**
     * 获取热门文章
     * @return 热门文章列表
     */
    ResponseResult<List<HotArticlesVo>> hotArticlesList();

    /**
     * 获取文章列表
     * @param categoryId 分类id
     * @param pageDto 分页参数
     * @return 文章列表
     */
    ResponseResult<PageVo<ArticleListVo>> getArticleList(Long categoryId, PageDto pageDto);

    /**
     * 获取文章详情
     * @param id 文章id
     * @return 文章详情
     */
    ResponseResult<ArticleDetailVo> getArticleDetail(Long id);

    /**
     * 更新文章浏览量
     * @param id 文章id
     * @return 更新结果
     */
    ResponseResult<Object> updateViewCount(Long id);

    /**
     * 添加文章
     * @param articleDto 文章信息
     * @return 添加结果
     */
    ResponseResult<Object> addArticle(ArticleDto articleDto);

    /**
     * 后台获取文章列表
     * @param queryArticleListDto 查询参数
     * @param pageDto 分页参数
     * @return 文章列表
     */
    ResponseResult<PageVo<ArticleListVo>> getAdminArticleList(QueryArticleListDto queryArticleListDto, PageDto pageDto);

    /**
     * 后台获取文章详情
     * @param id 文章id
     * @return 文章详情
     */
    ResponseResult<ArticleDto> getAdminArticleDetail(Long id);

    /**
     * 后台编辑文章
     * @param articleDto 文章信息
     * @return 更新结果
     */
    ResponseResult<Object> updateArticle(ArticleDto articleDto);

    /**
     * 更新文章浏览量
     * @param id 文章id
     * @param viewCount 浏览量
     * @return 更新结果
     */
    boolean updateViewCountById(Long id,Long viewCount);

    /**
     * 删除文章
     * @param id 文章id
     * @return 删除结果
     */
    ResponseResult<Object> deleteArticle(Long id);
}

