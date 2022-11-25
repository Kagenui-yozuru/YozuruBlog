package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.vo.ArticleDetailVo;
import com.yozuru.domain.vo.ArticleListVo;
import com.yozuru.domain.vo.HotArticlesVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yozuru
 */
@Api(tags = "文章",value = "文章相关的接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @SystemLog(businessName = "获取热门文章列表")
    @ApiOperation("获取热门文章列表")
    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticlesVo>> getList(){
        return articleService.hotArticlesList();
    }

    @SystemLog(businessName = "分页获取文章列表")
    @ApiOperation("分页获取文章列表")
    @GetMapping("/articleList")
    public ResponseResult<PageVo<ArticleListVo>> getArticleList(Long categoryId, Integer pageNum, Integer pageSize){
        return articleService.getArticleList(categoryId, pageNum, pageSize);
    }
    @SystemLog(businessName = "获取文章详情")
    @ApiOperation("获取文章详情")
    @GetMapping("/detail/{id}")
    public ResponseResult<ArticleDetailVo> getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @SystemLog(businessName = "更新文章浏览量")
    @ApiOperation("更新指定文章的浏览量")
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult<Object> updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}

