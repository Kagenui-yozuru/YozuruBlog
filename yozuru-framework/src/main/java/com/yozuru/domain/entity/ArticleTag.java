package com.yozuru.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author Yozuru
 * @since 2022-12-02 20:01:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("yozuru_article_tag")
public class ArticleTag  {
    //文章id
    private Long articleId;
    //标签id
    private Long tagId;

}

