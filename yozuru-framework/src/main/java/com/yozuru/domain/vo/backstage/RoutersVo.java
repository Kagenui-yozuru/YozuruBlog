package com.yozuru.domain.vo.backstage;


import com.yozuru.domain.vo.backstage.MenuVo;
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
