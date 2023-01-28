package com.yozuru.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.QueryCategoryDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.CategoryListVo;
import com.yozuru.domain.vo.forestage.CategoryVo;
import com.yozuru.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yozuru
 */

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult<List<CategoryVo>> listAllCategory() {
        return categoryService.getAllCategory();
    }
    @GetMapping("/list")
    public ResponseResult<PageVo<CategoryListVo>> listCategory(PageDto pageDto, QueryCategoryDto queryCategoryDto) {
        return categoryService.listCategoryByPage(pageDto, queryCategoryDto);
    }



}
