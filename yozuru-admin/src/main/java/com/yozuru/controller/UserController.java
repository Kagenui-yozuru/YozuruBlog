package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.QueryUserDto;
import com.yozuru.domain.dto.UserDto;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.SimpleRoleVo;
import com.yozuru.domain.vo.UpdateUserVo;
import com.yozuru.domain.vo.UserListVo;
import com.yozuru.service.RoleService;
import com.yozuru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult<PageVo<UserListVo>> getUserList(PageDto pageDto, QueryUserDto queryUserDto){
        return userService.getUserListByPage(queryUserDto,pageDto);
    }

    @PostMapping
    public ResponseResult<Object> addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/{id}")
    public ResponseResult<UpdateUserVo> getUserInfo(@PathVariable Long id){
        UpdateUserVo userVo = userService.getUserInfoById(id);
        ResponseResult<List<SimpleRoleVo>> roles = roleService.getAllRole();
        userVo.setRoles(roles.getData());
        return ResponseResult.success(userVo);
    }
    @PutMapping
    public ResponseResult<Object> updateUserInfo(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
}
