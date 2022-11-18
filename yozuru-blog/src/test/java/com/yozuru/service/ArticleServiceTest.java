package com.yozuru.service;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.vo.ArticleListVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yozuru
 */
@SpringBootTest
public class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void testGetArticleList() {
        ResponseResult<PageVo<ArticleListVo>> articleList =
                articleService.getArticleList(1L, 1, 10);
        System.out.println(articleList);
    }
}
