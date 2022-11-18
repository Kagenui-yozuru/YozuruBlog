package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Category;
import com.yozuru.domain.vo.GetCategoryListVo;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-18 00:34:47
 */

public interface CategoryService extends IService<Category> {
    ResponseResult<List<GetCategoryListVo>> getCategoryList();
}

