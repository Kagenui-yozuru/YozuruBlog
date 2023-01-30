package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.backstage.QueryUserDto;
import com.yozuru.domain.dto.backstage.UserDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.SimpleRoleVo;
import com.yozuru.domain.vo.backstage.UpdateUserVo;
import com.yozuru.domain.vo.backstage.UserVo;
import com.yozuru.service.backstage.RoleService;
import com.yozuru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :Yozuru
 * @since :2023/1/28 18:48
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('system:user:query')")
    public ResponseResult<PageVo<UserVo>> getUserList(PageDto pageDto, QueryUserDto queryUserDto){
        return userService.getUserListByPage(queryUserDto,pageDto);
    }

    @PostMapping
    @PreAuthorize("@ps.hasPermission('system:user:add')")
    public ResponseResult<Object> addUser(@RequestBody @Validated(UserDto.Add.class) UserDto userDto){
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:user:remove')")
    public ResponseResult<Object> deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:user:edit')")
    public ResponseResult<UpdateUserVo> getUserInfo(@PathVariable Long id){
        UpdateUserVo userVo = userService.getUserInfoById(id);
        ResponseResult<List<SimpleRoleVo>> roles = roleService.getAllRole();
        userVo.setRoles(roles.getData());
        return ResponseResult.success(userVo);
    }
    @PutMapping
    @PreAuthorize("@ps.hasPermission('system:user:edit')")
    public ResponseResult<Object> updateUserInfo(@RequestBody @Validated(UserDto.Update.class) UserDto userDto){
        return userService.updateUser(userDto);
    }
}
