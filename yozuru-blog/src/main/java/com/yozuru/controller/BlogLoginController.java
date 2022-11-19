package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.BlogLoginDto;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.BlogUserLoginVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.service.BlogLoginService;
import io.jsonwebtoken.lang.Strings;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yozuru
 */
@RestController
public class BlogLoginController {
    @Autowired
    BlogLoginService loginService;

    @PostMapping("/login")
    public ResponseResult<BlogUserLoginVo> login(@RequestBody BlogLoginDto loginDto){
        if (!Strings.hasText(loginDto.getUserName())){
            throw new BusinessException(HttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseResult<Object> logout(){
        return loginService.logout();
    }
}
