package com.yozuru.job;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yozuru.domain.constants.RedisConstant;
import com.yozuru.domain.entity.Article;
import com.yozuru.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yozuru
 */
@Component
@Slf4j
public class UpdateViewCount {

    @CreateCache(area = "viewCount",name = RedisConstant.VIEW_COUNT_KEY_PREFIX,cacheType = CacheType.REMOTE)
    private Cache<Long, Long> viewCountCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCount() {
        log.info("执行定时任务：更新文章浏览量");
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getId, Article::getViewCount);
        List<Article> articles = articleService.list(queryWrapper);

        articles.forEach(article -> {
            Long viewCount = viewCountCache.get(article.getId());
            if (!viewCount.equals(article.getViewCount())) {
                article.setViewCount(viewCount);
                articleService.updateById(article);
            }
        });
    }
}
