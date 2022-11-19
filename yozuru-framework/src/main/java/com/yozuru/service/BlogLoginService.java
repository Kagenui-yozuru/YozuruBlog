package com.yozuru.service;


import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.BlogLoginDto;
import com.yozuru.domain.vo.BlogUserLoginVo;

/**
 * 前台登录服务接口
 *
 * @author Yozuru
 */

public interface BlogLoginService {
    ResponseResult<BlogUserLoginVo> login(BlogLoginDto loginDto);

    ResponseResult<Object> logout();
}