package com.yozuru.domain.dto.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 条件查询文章列表的dto
 * @author Yozuru
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryArticleListDto {
    private String title;
    private String summary;
}
