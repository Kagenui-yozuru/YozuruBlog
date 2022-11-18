package com.yozuru.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class GetCategoryListVo implements Serializable {
    private Long id;
    private String name;
}
