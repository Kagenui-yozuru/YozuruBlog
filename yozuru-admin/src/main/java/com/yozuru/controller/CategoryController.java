package com.yozuru.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.CategoryDto;
import com.yozuru.domain.dto.backstage.QueryCategoryDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.CategoryListVo;
import com.yozuru.domain.vo.forestage.CategoryVo;
import com.yozuru.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yozuru
 */

@RestController
@RequestMapping("/content/category")
@PreAuthorize("@ps.hasPermission('content:category:list')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    @PreAuthorize("@ps.hasPermission('content:article:writer')")
    public ResponseResult<List<CategoryVo>> listAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/list")
    public ResponseResult<PageVo<CategoryListVo>> listCategory(PageDto pageDto, QueryCategoryDto queryCategoryDto) {
        return categoryService.listCategoryByPage(pageDto, queryCategoryDto);
    }
    @PostMapping
    public ResponseResult<Object> addCategory(@RequestBody @Validated({CategoryDto.Add.class}) CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseResult<CategoryDto> getCategory(@PathVariable("id") Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping
    public ResponseResult<Object> updateCategory(@RequestBody @Validated({CategoryDto.Update.class})CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }
    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

}
