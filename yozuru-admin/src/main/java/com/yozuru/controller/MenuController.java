package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.vo.backstage.MenuIdVo;
import com.yozuru.domain.vo.backstage.MenuVo;
import com.yozuru.domain.vo.backstage.SimpleMenuVo;
import com.yozuru.service.backstage.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :Yozuru
 * @since :2023/1/27 18:58
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('system:menu:list')")
    public ResponseResult<List<MenuVo>> getMenuList(String status, String name) {
        return menuService.selectMenuList(name, status);
    }
    @PostMapping
    @PreAuthorize("@ps.hasPermission('system:menu:add')")
    public ResponseResult<Object> addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @PutMapping
    @PreAuthorize("@ps.hasPermission('system:menu:edit')")
    public ResponseResult<Object> updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:menu:remove')")
    public ResponseResult<Object> deleteMenu(@PathVariable Long id) {
        return menuService.deleteMenu(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:menu:edit')")
    public ResponseResult<MenuVo> getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }
    @GetMapping("/selectTreeAll")
    @PreAuthorize("@ps.hasPermission('system:menu:edit','system:role:edit','system:role:add')")
    public ResponseResult<List<SimpleMenuVo>> selectTreeAll() {
        return menuService.selectTreeAll();
    }
    @GetMapping("/selectMenuIdByRoleId/{id}")
    @PreAuthorize("@ps.hasPermission('system:role:edit')")
    public ResponseResult<MenuIdVo> selectTreeByRoleId(@PathVariable Long id) {
        List<Long> longs = menuService.selectMenuIdsByRoleId(id);
        List<SimpleMenuVo> simpleMenuVos = menuService.selectTreeAll().getData();
        MenuIdVo menuIdVo = new MenuIdVo(longs, simpleMenuVos);
        return ResponseResult.success(menuIdVo);
    }
}
