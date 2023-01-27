package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.vo.MenuIdVo;
import com.yozuru.domain.vo.MenuVo;
import com.yozuru.domain.vo.SimpleMenuVo;
import com.yozuru.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult<List<MenuVo>> getMenuList(String status, String name) {
        return menuService.selectMenuList(name, status);
    }
    @PostMapping
    public ResponseResult<Object> addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @PutMapping
    public ResponseResult<Object> updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteMenu(@PathVariable Long id) {
        return menuService.deleteMenu(id);
    }

    @GetMapping("/{id}")
    public ResponseResult<MenuVo> getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id);
    }
    @GetMapping("/selectTreeAll")
    public ResponseResult<List<SimpleMenuVo>> selectTreeAll() {
        return menuService.selectTreeAll();
    }
    @GetMapping("/selectMenuIdByRoleId/{id}")
    public ResponseResult<MenuIdVo> selectTreeByRoleId(@PathVariable Long id) {
        List<Long> longs = menuService.selectMenuIdsByRoleId(id);
        List<SimpleMenuVo> simpleMenuVos = menuService.selectTreeAll().getData();
        MenuIdVo menuIdVo = new MenuIdVo(longs, simpleMenuVos);
        return ResponseResult.success(menuIdVo);
    }
}
