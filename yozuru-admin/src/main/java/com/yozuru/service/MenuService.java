package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.vo.MenuVo;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-29 17:45:18
 */

public interface MenuService extends IService<Menu> {
    /**
     * 根据用户ID查询该用户的权限关键字
     * @param id 用户ID
     * @return 权限关键字列表
     */
    List<String> selectPermsByUserId(Long id);

    /**
     * 根据用户ID查询该用户的菜单树
     * @param id 用户ID
     * @return 菜单树
     */
    List<MenuVo> selectRouterMenuTreeByUserId(Long id);
}

