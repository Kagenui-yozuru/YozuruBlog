package com.yozuru.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.UpdateUserInfoDto;
import com.yozuru.domain.vo.UserInfoVo;
import com.yozuru.mapper.UserMapper;
import com.yozuru.domain.entity.User;
import com.yozuru.service.UserService;
import com.yozuru.utils.BeanCopyUtil;
import com.yozuru.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-24 16:06:45
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
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
}

