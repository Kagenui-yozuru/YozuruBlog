package com.yozuru.domain.vo.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yozuru
 * @since :2023/1/29 1:52
 */
@Data
@NoArgsConstructor
public class CategoryListVo {
    private Long id;
    private String name;
    private Integer status;
    private String description;
}
