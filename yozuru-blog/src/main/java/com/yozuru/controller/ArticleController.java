package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.vo.ArticleDetailVo;
import com.yozuru.domain.vo.ArticleListVo;
import com.yozuru.domain.vo.HotArticlesVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yozuru
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult<List<HotArticlesVo>> getList(){
        return articleService.hotArticlesList();
    }

    @GetMapping("/articleList")
    public ResponseResult<PageVo<ArticleListVo>> getArticleList(Long categoryId, Integer pageNum, Integer pageSize){
        return articleService.getArticleList(categoryId, pageNum, pageSize);
    }

    @GetMapping("/detail/{id}")
    public ResponseResult<ArticleDetailVo> getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}

