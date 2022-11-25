package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.RegisterDto;
import com.yozuru.domain.dto.UpdateUserInfoDto;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.UserInfoVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.mapper.UserMapper;
import com.yozuru.domain.entity.User;
import com.yozuru.service.UserService;
import com.yozuru.utils.BeanCopyUtil;
import com.yozuru.utils.SecurityUtils;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-24 16:06:45
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseResult<UserInfoVo> getUserInfo() {
        //获得用户ID
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(user, UserInfoVo.class);
        return ResponseResult.success(userInfoVo);
    }

    @Override
    public ResponseResult<Object> updateUserInfo(UpdateUserInfoDto userInfoDto) {
        User user = BeanCopyUtil.copyBean(userInfoDto, User.class);
        user.setId(SecurityUtils.getUserId());
        updateById(user);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> register(RegisterDto registerDto) {
        //registerDto中已经对数据格式进行过校验了
        //进行唯一判断
        if (userNameExit(registerDto.getUserName()))
            throw new BusinessException(HttpCodeEnum.USERNAME_EXIST);
        if (emailExit(registerDto.getEmail()))
            throw new BusinessException(HttpCodeEnum.EMAIL_EXIST);
        //对密码进行加密
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        User user = BeanCopyUtil.copyBean(registerDto, User.class);
        save(user);
        return ResponseResult.success();
    }

    private boolean userNameExit(String userName){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        return count(queryWrapper) > 0;
    }
    private boolean emailExit(String email){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email);
        return count(queryWrapper) > 0;
    }
}

