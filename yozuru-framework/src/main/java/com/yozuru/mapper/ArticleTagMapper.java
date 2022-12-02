package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-12-02 20:01:14
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}

