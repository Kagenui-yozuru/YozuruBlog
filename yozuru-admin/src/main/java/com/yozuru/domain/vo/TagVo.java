package com.yozuru.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    private Long id;
    private String name;
    private String remark;
}
