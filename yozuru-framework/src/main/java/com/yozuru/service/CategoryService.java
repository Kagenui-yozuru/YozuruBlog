package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Category;
import com.yozuru.domain.vo.CategoryVo;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-18 00:34:47
 */

public interface CategoryService extends IService<Category> {
    /**
     * 获取有效的分类列表（分类状态为可用且分类下有文章）
     * @return  有效分类的列表
     */
    ResponseResult<List<CategoryVo>> getCategoryList();

    /**
     * 获得所有状态为可用的分类，包括没有文章的分类
     * @return  分类列表
     */
    ResponseResult<List<CategoryVo>> getAllCategory();
}

