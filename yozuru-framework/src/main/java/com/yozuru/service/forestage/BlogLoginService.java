package com.yozuru.service.forestage;


import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.LoginDto;
import com.yozuru.domain.vo.forestage.UserLoginVo;

/**
 * 前台登录服务接口
 *
 * @author Yozuru
 */

public interface BlogLoginService {
    ResponseResult<UserLoginVo> login(LoginDto loginDto);

    ResponseResult<Object> logout();
}