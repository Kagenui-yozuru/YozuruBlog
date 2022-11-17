package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Article;
import com.yozuru.domain.vo.HotArticlesVo;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */

public interface ArticleService extends IService<Article> {

    ResponseResult<List<HotArticlesVo>> hotArticlesList();
}

