package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.RegisterDto;
import com.yozuru.domain.dto.UpdateUserInfoDto;
import com.yozuru.domain.entity.User;
import com.yozuru.domain.vo.UserInfoVo;

/**
 * 用户表(User)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-24 16:06:45
 */

public interface UserService extends IService<User> {

    ResponseResult<UserInfoVo> getUserInfo();

    ResponseResult<Object> updateUserInfo(UpdateUserInfoDto userInfoDto);

    ResponseResult<Object> register(RegisterDto registerDto);
}

