package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.RegisterDto;
import com.yozuru.domain.dto.UpdateUserInfoDto;
import com.yozuru.domain.vo.UserInfoVo;
import com.yozuru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yozuru
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<UserInfoVo> getUserInfo(){
        return userService.getUserInfo();
    }
    @PutMapping("/userInfo")
    public ResponseResult<Object> updateUserInfo(@RequestBody UpdateUserInfoDto userInfoDto){
        return userService.updateUserInfo(userInfoDto);
    }
    @PostMapping("/register")
    public ResponseResult<Object> register(@RequestBody RegisterDto registerDto){
        return userService.register(registerDto);
    }
}
