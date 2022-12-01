package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.LoginDto;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.BlogUserLoginVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.service.BlogLoginService;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yozuru
 */
@RestController
@Api(tags = "登录",value = "前台用户登录相关的接口")
public class BlogLoginController {
    @Autowired
    BlogLoginService loginService;

    @SystemLog(businessName = "用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult<BlogUserLoginVo> login(@RequestBody LoginDto loginDto){
        if (!Strings.hasText(loginDto.getUserName())){
            throw new BusinessException(HttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(loginDto);
    }

    @SystemLog(businessName = "用户登出")
    @ApiOperation("用户登出，需要携带token")
    @PostMapping("/logout")
    public ResponseResult<Object> logout(){
        return loginService.logout();
    }
}
