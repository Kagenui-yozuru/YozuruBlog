package com.yozuru.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutersVo {

    private List<MenuVo> menus;
}
