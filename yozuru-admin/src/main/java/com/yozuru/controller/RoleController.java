package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.CategoryDto;
import com.yozuru.domain.dto.backstage.RoleDto;
import com.yozuru.domain.dto.backstage.RoleStatusDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.RoleVo;
import com.yozuru.domain.vo.backstage.SimpleRoleVo;
import com.yozuru.service.backstage.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :Yozuru
 * @since :2023/1/28 3:41
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('system:role:query')")
    public ResponseResult<PageVo<RoleVo>> listByPage(PageDto pageDto, String roleName, String status) {
        return roleService.getRoleListByPage(pageDto, roleName, status);
    }
    @PutMapping("/updateStatus")
    @PreAuthorize("@ps.hasPermission('system:role:edit')")
    public ResponseResult<Object> changeStatus(@RequestBody RoleStatusDto roleStatusDto) {
        return roleService.updateStatus(roleStatusDto.getRoleId(), roleStatusDto.getStatus());
    }
    @PostMapping
    @PreAuthorize("@ps.hasPermission('system:role:add')")
    public ResponseResult<Object> addRole(@RequestBody @Validated(RoleDto.Add.class)RoleDto roleDto) {
        return roleService.addRole(roleDto);
    }
    @GetMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:role:edit')")
    public ResponseResult<RoleVo> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
    @PutMapping
    @PreAuthorize("@ps.hasPermission('system:role:edit')")
    public ResponseResult<Object> updateRole(@RequestBody @Validated(RoleDto.Update.class)RoleDto roleDto) {
        return roleService.updateRole(roleDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:role:remove')")
    public ResponseResult<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRoleById(id);
    }
    @GetMapping("/listAllRole")
    @PreAuthorize("@ps.hasPermission('system:user:add','system:user:edit','system:role:edit')")
    public ResponseResult<List<SimpleRoleVo>> listAllRole() {
        return roleService.getAllRole();
    }
}
