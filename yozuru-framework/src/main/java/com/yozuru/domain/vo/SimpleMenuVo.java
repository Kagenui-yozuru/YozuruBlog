package com.yozuru.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :Yozuru
 * @since :2023/1/28 2:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMenuVo {
    private Long id;
    private String label;
    private Long parentId;
    private List<? extends SimpleMenuVo> children;
}
