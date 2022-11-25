package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.vo.GetCategoryListVo;
import com.yozuru.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yozuru
 */
@Api(tags = "文章分类",value = "文章分类相关的接口")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @SystemLog(businessName = "获得分类列表")
    @GetMapping("/getCategoryList")
    public ResponseResult<List<GetCategoryListVo>> getCategoryList(){
       return categoryService.getCategoryList();
    }
}
