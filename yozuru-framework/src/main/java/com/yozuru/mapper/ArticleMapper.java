package com.yozuru.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}

