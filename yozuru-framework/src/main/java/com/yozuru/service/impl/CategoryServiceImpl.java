package com.yozuru.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.mapper.CategoryMapper;
import com.yozuru.domain.entity.Category;
import com.yozuru.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 分类表(Category)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-18 00:34:47
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

