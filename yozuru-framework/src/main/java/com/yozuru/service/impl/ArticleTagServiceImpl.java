package com.yozuru.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.mapper.ArticleTagMapper;
import com.yozuru.domain.entity.ArticleTag;
import com.yozuru.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author Yozuru
 * @since 2022-12-02 20:01:14
 */

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

