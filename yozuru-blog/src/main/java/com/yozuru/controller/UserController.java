package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.forestage.RegisterDto;
import com.yozuru.domain.dto.forestage.UserInfoDto;
import com.yozuru.domain.vo.forestage.UserInfoVo;
import com.yozuru.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yozuru
 */
@Api(tags = "用户",value = "用户相关的接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @SystemLog(businessName = "获取用户信息")
    @ApiOperation("获取当前登录用户的信息，需要传递token")
    @GetMapping("/userInfo")
    public ResponseResult<UserInfoVo> getUserInfo(){
        return userService.getUserInfo();
    }
    @SystemLog(businessName = "更新用户信息")
    @ApiOperation("更新当前登录用户的信息，需要传递token")
    @PutMapping("/userInfo")
    public ResponseResult<Object> updateUserInfo(@RequestBody UserInfoDto userInfoDto){
        return userService.updateUserInfo(userInfoDto);
    }
    @SystemLog(businessName = "用户注册")
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseResult<Object> register(@RequestBody RegisterDto registerDto){
        return userService.register(registerDto);
    }
}
