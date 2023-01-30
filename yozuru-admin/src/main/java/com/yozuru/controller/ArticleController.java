package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.backstage.ArticleDto;
import com.yozuru.domain.dto.backstage.QueryArticleListDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.vo.forestage.ArticleListVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yozuru
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")
    public ResponseResult<Object> addArticle(@RequestBody @Validated(ArticleDto.Add.class) ArticleDto articleDto) {
        return articleService.addArticle(articleDto);
    }

    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('content:article:list')")
    public ResponseResult<PageVo<ArticleListVo>> getArticleList(QueryArticleListDto queryArticleListDto, PageDto pageDto) {
        return articleService.getAdminArticleList(queryArticleListDto,pageDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('content:article:writer')")
    public ResponseResult<ArticleDto> getArticleDetail(@PathVariable Long id) {
        return articleService.getAdminArticleDetail(id);
    }

    @PutMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")
    public ResponseResult<Object> updateArticle(@RequestBody @Validated(ArticleDto.Update.class)ArticleDto articleDto) {
        return articleService.updateArticle(articleDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('content:article:list')")
    public ResponseResult<Object> deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

}
