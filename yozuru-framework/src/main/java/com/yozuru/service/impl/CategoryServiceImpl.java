package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.CategoryDto;
import com.yozuru.domain.dto.backstage.QueryCategoryDto;
import com.yozuru.domain.entity.Article;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.CategoryListVo;
import com.yozuru.domain.vo.forestage.CategoryVo;
import com.yozuru.mapper.ArticleMapper;
import com.yozuru.mapper.CategoryMapper;
import com.yozuru.domain.entity.Category;
import com.yozuru.service.CategoryService;
import com.yozuru.utils.BeanCopyUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-18 00:34:47
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public ResponseResult<List<CategoryVo>> getCategoryList() {
        //查询文章表 查询条件为状态为已发布的文章，按照分组id分组。
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstant.ARTICLES_STATUS_NORMAL)
                        .select(Article::getCategoryId)
                        .groupBy(Article::getCategoryId);
        List<Article> articleList = articleMapper.selectList(articleWrapper);
        //获得分组id的集合
        List<Long> collect = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toList());
        //根据分类id查询分类表 分组须为可用状态。
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
        categoryWrapper.in(Category::getId, collect)
                        .eq(Category::getStatus, SystemConstant.STATUS_NORMAL)
                        .select(Category::getId, Category::getName);
        List<Category> categoryList = list(categoryWrapper);
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categoryList, CategoryVo.class);
        //封装vo
        return ResponseResult.success(categoryVos);
    }

    @Override
    public ResponseResult<List<CategoryVo>> getAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstant.STATUS_NORMAL)
                .select(Category::getId, Category::getName);
        List<Category> categoryList = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtil.copyBeanList(categoryList, CategoryVo.class);
        return ResponseResult.success(categoryVos);
    }

    @Override
    public ResponseResult<PageVo<CategoryListVo>> listCategoryByPage(PageDto pageDto, QueryCategoryDto queryCategoryDto) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotEmpty(queryCategoryDto.getName()),Category::getName, queryCategoryDto.getName())
                .eq(queryCategoryDto.getStatus()!=null,Category::getStatus, queryCategoryDto.getStatus())
                .orderByDesc(Category::getCreateTime);
        Page<Category> categoryPage = page(new Page<>(pageDto.getPageNum(), pageDto.getPageSize()), wrapper);
        List<Category> categoryList = categoryPage.getRecords();
        List<CategoryListVo> categoryListVos = BeanCopyUtil.copyBeanList(categoryList, CategoryListVo.class);
        PageVo<CategoryListVo> pageVo = new PageVo<>(categoryListVos, categoryPage.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override
    public ResponseResult<Object> addCategory(CategoryDto categoryDto) {
        Category category = BeanCopyUtil.copyBean(categoryDto, Category.class);
        save(category);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<CategoryDto> getCategoryById(Integer id) {
        Category category = getById(id);
        CategoryDto categoryDto = BeanCopyUtil.copyBean(category, CategoryDto.class);
        return ResponseResult.success(categoryDto);
    }

    @Override
    public ResponseResult<Object> deleteCategory(Long id) {
        removeById(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> updateCategory(CategoryDto categoryDto) {
        Category category = BeanCopyUtil.copyBean(categoryDto, Category.class);
        updateById(category);
        return ResponseResult.success();
    }
}

