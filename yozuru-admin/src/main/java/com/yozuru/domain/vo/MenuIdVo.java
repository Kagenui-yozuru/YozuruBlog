package com.yozuru.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :Yozuru
 * @since :2023/1/28 4:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuIdVo {
    private List<Long> checkedKeys;
    private List<SimpleMenuVo> menus;

}
