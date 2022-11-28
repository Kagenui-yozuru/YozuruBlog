package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.BlogLoginDto;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.exception.BusinessException;
import com.yozuru.service.AdminLoginService;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Yozuru
 */
@RestController
@Api(tags = "登录",value = "后台用户登录相关的接口")
public class AdminLoginController {
    @Autowired
    AdminLoginService loginService;

    @SystemLog(businessName = "用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult<Map<String,String>> login(@RequestBody BlogLoginDto loginDto){
        if (!Strings.hasText(loginDto.getUserName())){
            throw new BusinessException(HttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(loginDto);
    }
}
