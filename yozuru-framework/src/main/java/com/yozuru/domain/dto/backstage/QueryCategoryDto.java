package com.yozuru.domain.dto.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yozuru
 * @since :2023/1/29 1:39
 */
@Data
@NoArgsConstructor
public class QueryCategoryDto {
    private String name;
    private Integer status;
}
