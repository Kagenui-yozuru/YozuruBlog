package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.vo.CategoryVo;
import com.yozuru.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/listAllCategory")
    public ResponseResult<List<CategoryVo>> listAllCategory() {
        return categoryService.getAllCategory();
    }
}
