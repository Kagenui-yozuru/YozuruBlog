package com.yozuru.service.backstage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.vo.backstage.MenuVo;
import com.yozuru.domain.vo.backstage.SimpleMenuVo;

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
    ResponseResult<List<MenuVo>> selectMenuList(String menuName, String status);
    ResponseResult<Object> addMenu(Menu menu);

    ResponseResult<MenuVo> getMenuById(Long id);

    ResponseResult<Object> updateMenu(Menu menu);

    ResponseResult<Object> deleteMenu(Long id);

    ResponseResult<List<SimpleMenuVo>> selectTreeAll();
    ResponseResult<List<SimpleMenuVo>> selectTreeByRoleId(Long id);

    List<Long> selectMenuIdsByRoleId(Long id);

}

