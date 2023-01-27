package com.yozuru.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.RedisConstant;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.dto.ArticleDto;
import com.yozuru.domain.dto.ArticleListDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.entity.ArticleTag;
import com.yozuru.domain.entity.Category;
import com.yozuru.domain.vo.*;
import com.yozuru.mapper.ArticleMapper;
import com.yozuru.domain.entity.Article;
import com.yozuru.mapper.CategoryMapper;
import com.yozuru.service.ArticleService;
import com.yozuru.service.ArticleTagService;
import com.yozuru.utils.BeanCopyUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleTagService articleTagService;

    @CreateCache(area = "viewCount",name = RedisConstant.VIEW_COUNT_KEY_PREFIX,cacheType = CacheType.REMOTE)
    private Cache<Long, Long> viewCountCache;

    @Override
    public ResponseResult<List<HotArticlesVo>> hotArticlesList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        状态为已发布即状态码为0
        queryWrapper.select(Article::getId, Article::getTitle, Article::getViewCount)
                .eq(Article::getStatus, SystemConstant.ARTICLES_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount)
                .last("limit "+SystemConstant.HOT_ARTICLES_LIST_SIZE);


        List<Article> list = list(queryWrapper);
        List<HotArticlesVo> hotArticlesVos = BeanCopyUtil.copyBeanList(list, HotArticlesVo.class);
        hotArticlesVos.forEach(hotArticlesVo ->
            hotArticlesVo.setViewCount(viewCountCache.get(hotArticlesVo.getId()))
        );
        return ResponseResult.success(hotArticlesVos);
    }

    @Override
    public ResponseResult<PageVo<ArticleListVo>> getArticleList(Long categoryId, PageDto pageDto) {
        IPage<Article> pageData = new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
        //分页查询文章，按照创建时间倒序、并将置顶的文章排在前面。为减少传输数据量，只查询文章的id、标题、摘要、缩略图、访问量、创建时间、分类id。
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId, Article::getTitle, Article::getSummary, Article::getCategoryId, Article::getThumbnail, Article::getStatus,Article::getCreateTime)
                .eq(Article::getStatus, SystemConstant.ARTICLES_STATUS_NORMAL)
                .eq(categoryId!=null&&categoryId!=0L,Article::getCategoryId, categoryId)
                .orderByDesc(Article::getIsTop)
                .orderByDesc(Article::getCreateTime);
        pageData = page(pageData, queryWrapper);
        //获得分类的id集合
        List<Article> records = pageData.getRecords();
        Set<Long> categoryIds = records.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());

        //把分类的id集合传入到map中，查询分类的id和分类的名称
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.select(Category::getId, Category::getName)
                .in(Category::getId, categoryIds);
        List<Category> categories = categoryMapper.selectList(categoryWrapper);
        //把分类的id和分类的名称放入到map中，方便后面的查询
        Map<Long, String> categoryMap = categories.stream()
                .collect(Collectors.toMap(Category::getId, Category::getName));
        //把文章的分类id替换成分类的名称
        records = records.stream()
                .peek(article ->
                        article.setCategoryName(categoryMap.get(article.getCategoryId()))
                                .setViewCount(viewCountCache.get(article.getId()))
                )
                .collect(Collectors.toList());

        //封装进VO
        List<ArticleListVo> list = BeanCopyUtil.copyBeanList(records, ArticleListVo.class);
        PageVo<ArticleListVo> pageVo = new PageVo<>(list, pageData.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override
    public ResponseResult<ArticleDetailVo> getArticleDetail(Long id) {
        Article article = getById(id);

        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.select(Category::getName)
                .eq(Category::getId, article.getCategoryId());

        Category category = categoryMapper.selectOne(categoryWrapper);

        article.setCategoryName(category.getName())
                .setViewCount(viewCountCache.get(article.getId()));
        ArticleDetailVo articleDetailVo = BeanCopyUtil.copyBean(article, ArticleDetailVo.class);
        return ResponseResult.success(articleDetailVo);
    }

    @Override
    public ResponseResult<Object> updateViewCount(Long id) {
        long l = viewCountCache.get(id) + 1;
        viewCountCache.put(id,l);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> addArticle(ArticleDto articleDto) {
        Article article = BeanCopyUtil.copyBean(articleDto, Article.class);
        //把文章存入数据库
        save(article);
        //把文章和标签的关系存入数据库
        List<Long> tagIds = articleDto.getTags();
        List<ArticleTag> articleTagList = tagIds.stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTagList);
        //添加文章浏览量的缓存
        viewCountCache.put(article.getId(),0L);

        return ResponseResult.success();
    }

    @Override
    public ResponseResult<PageVo<ArticleListVo>> getAdminArticleList(ArticleListDto articleListDto, PageDto pageDto) {
        //设置条件查询
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotBlank(articleListDto.getTitle()),Article::getTitle,articleListDto.getTitle())
                .like(Strings.isNotBlank(articleListDto.getSummary()),Article::getSummary,articleListDto.getSummary())
                .orderByDesc(Article::getIsTop)
                .orderByDesc(Article::getCreateTime);
        //设置分页查询
        Page<Article> pageData = new Page<>(pageDto.getPageNum(), pageDto.getPageSize());
        //查询文章列表
        pageData = page(pageData, queryWrapper);
        //封装进VO
        List<Article> records = pageData.getRecords();
        List<ArticleListVo> adminArticleVoList = BeanCopyUtil.copyBeanList(records, ArticleListVo.class);
        PageVo<ArticleListVo> pageVo = new PageVo<>(adminArticleVoList, pageData.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override
    public ResponseResult<ArticleDto> getAdminArticleDetail(Long id) {
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getId, id);
        Article article = getOne(articleWrapper);
        ArticleDto articleDto = BeanCopyUtil.copyBean(article, ArticleDto.class);
        //查询文章的标签
        LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
        articleTagWrapper.select(ArticleTag::getTagId)
                .eq(ArticleTag::getArticleId, id);
        List<Long> tagList = articleTagService.list(articleTagWrapper)
                .stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());
        articleDto.setTags(tagList);
        return ResponseResult.success(articleDto);
    }

    @Override
    public ResponseResult<Object> updateArticle(ArticleDto articleDto) {
        Article article = BeanCopyUtil.copyBean(articleDto, Article.class);
        //更新文章
        updateById(article);
        //更新文章和标签的关系
        LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
        articleTagWrapper.eq(ArticleTag::getArticleId, article.getId());
        //先删除原来的关系
        articleTagService.remove(articleTagWrapper);
        List<Long> tagIds = articleDto.getTags();
        List<ArticleTag> articleTagList = tagIds.stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        //再添加新的关系
        articleTagService.saveBatch(articleTagList);
        return ResponseResult.success();
    }

    @Override
    public boolean updateViewCountById(Long id, Long viewCount) {
       baseMapper.updateViewCountById(id,viewCount);
       return true;
    }

    @Override
    public ResponseResult<Object> deleteArticle(Long id) {
        //删除文章
        removeById(id);
        //删除文章和标签的关系
        LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
        articleTagWrapper.eq(ArticleTag::getArticleId, id);
        articleTagService.remove(articleTagWrapper);
        //删除文章浏览量的缓存
        viewCountCache.remove(id);
        return ResponseResult.success();
    }
}

