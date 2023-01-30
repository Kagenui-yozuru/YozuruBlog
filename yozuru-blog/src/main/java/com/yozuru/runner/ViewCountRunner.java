package com.yozuru.runner;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yozuru.domain.constants.RedisConstant;
import com.yozuru.domain.entity.Article;
import com.yozuru.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yozuru
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @CreateCache(area = "viewCount",name = RedisConstant.VIEW_COUNT_KEY_PREFIX,cacheType = CacheType.REMOTE)
    private Cache<Long, Long> viewCountCache;

    @Override
    public void run(String... args) throws Exception {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId, Article::getViewCount);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        articles.forEach(article ->
                viewCountCache.put(article.getId(), article.getViewCount()));
    }
}
