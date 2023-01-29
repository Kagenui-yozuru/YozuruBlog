package com.yozuru.domain.dto.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yozuru
 * @since :2023/1/29 16:10
 */
@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private Integer status;
}
