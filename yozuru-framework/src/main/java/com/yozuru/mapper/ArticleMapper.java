package com.yozuru.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-17 19:27:56
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
@Update("update yozuru_article set view_count = #{view_count} + 1 where id = #{id}")
    void updateViewCountById(@Param("id") Long id,@Param("view_count")  Long viewCount);

}

