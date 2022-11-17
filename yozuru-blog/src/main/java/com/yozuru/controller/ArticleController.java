package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Article;
import com.yozuru.domain.vo.HotArticlesVo;
import com.yozuru.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yozuru
 */

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticlesList")
    public ResponseResult<List<HotArticlesVo>> getList(){
        return articleService.hotArticlesList();
    }
}

