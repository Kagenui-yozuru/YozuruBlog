package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.*;
import com.yozuru.domain.dto.backstage.QueryUserDto;
import com.yozuru.domain.dto.backstage.UserDto;
import com.yozuru.domain.dto.forestage.RegisterDto;
import com.yozuru.domain.dto.forestage.UserInfoDto;
import com.yozuru.domain.entity.User;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.backstage.UpdateUserVo;
import com.yozuru.domain.vo.forestage.UserInfoVo;
import com.yozuru.domain.vo.backstage.UserVo;

/**
 * 用户表(User)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-24 16:06:45
 */

public interface UserService extends IService<User> {

    ResponseResult<UserInfoVo> getUserInfo();

    ResponseResult<Object> updateUserInfo(UserInfoDto userInfoDto);

    ResponseResult<Object> register(RegisterDto registerDto);

    ResponseResult<PageVo<UserVo>> getUserListByPage(QueryUserDto queryUserDto, PageDto pageDto);

    ResponseResult<Object> addUser(UserDto userDto);

    ResponseResult<Object> deleteUserById(Long id);

    UpdateUserVo getUserInfoById(Long id);

    ResponseResult<Object> updateUser(UserDto userDto);
}

