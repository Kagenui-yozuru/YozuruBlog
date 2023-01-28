package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.*;
import com.yozuru.domain.dto.backstage.QueryUserDto;
import com.yozuru.domain.dto.backstage.UserDto;
import com.yozuru.domain.dto.forestage.RegisterDto;
import com.yozuru.domain.dto.forestage.UserInfoDto;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.*;
import com.yozuru.domain.vo.backstage.UpdateUserInfoVo;
import com.yozuru.domain.vo.backstage.UpdateUserVo;
import com.yozuru.domain.vo.backstage.UserVo;
import com.yozuru.domain.vo.forestage.UserInfoVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.mapper.UserMapper;
import com.yozuru.domain.entity.User;
import com.yozuru.service.UserService;
import com.yozuru.utils.BeanCopyUtil;
import com.yozuru.utils.SecurityUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public ResponseResult<Object> updateUserInfo(UserInfoDto userInfoDto) {
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

    @Override
    public ResponseResult<PageVo<UserVo>> getUserListByPage(QueryUserDto queryUserDto, PageDto pageDto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Strings.isNotEmpty(queryUserDto.getUserName()),User::getUserName,queryUserDto.getUserName())
                .eq(Strings.isNotEmpty(queryUserDto.getPhonenumber()),User::getPhonenumber,queryUserDto.getPhonenumber())
                .eq(Strings.isNotEmpty(queryUserDto.getStatus()),User::getStatus,queryUserDto.getStatus())
                .orderByAsc(User::getCreateTime);
        Page<User> pageObj = new Page<>(pageDto.getPageNum(),pageDto.getPageSize());
        pageObj= page(pageObj, wrapper);
        List<User> users = pageObj.getRecords();
        List<UserVo> userVos = BeanCopyUtil.copyBeanList(users, UserVo.class);
        PageVo<UserVo> pageVo = new PageVo<>(userVos,pageObj.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Object> addUser(UserDto userDto) {
        if (userNameExit(userDto.getUserName()))
            throw new BusinessException(HttpCodeEnum.USERNAME_EXIST);
        if (emailExit(userDto.getEmail()))
            throw new BusinessException(HttpCodeEnum.EMAIL_EXIST);
        if (phoneExit(userDto.getPhonenumber()))
            throw new BusinessException(HttpCodeEnum.PHONENUMBER_EXIST);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user=BeanCopyUtil.copyBean(userDto,User.class);
        save(user);
        baseMapper.insertUserRole(user.getId(),userDto.getRoleIds());
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> deleteUserById(Long id) {
        removeById(id);
        baseMapper.deleteUserRole(id);
        return ResponseResult.success();
    }

    @Override
    public UpdateUserVo getUserInfoById(Long id) {
        User user = getById(id);
        UpdateUserInfoVo updateUserInfoVo = BeanCopyUtil.copyBean(user, UpdateUserInfoVo.class);
        UpdateUserVo updateUserVo = new UpdateUserVo();
        updateUserVo.setUser(updateUserInfoVo);
        List<Long> roleIds = baseMapper.getRoleIdsByUserId(id);
        updateUserVo.setRoleIds(roleIds);
        return updateUserVo;
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
    private boolean phoneExit(String phone){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhonenumber,phone);
        return count(queryWrapper) > 0;
    }

    @Override
    public ResponseResult<Object> updateUser(UserDto userDto) {
        //待优化
        User nowUser = getById(userDto.getId());
        if (!nowUser.getUserName().equals(userDto.getUserName())&&userNameExit(userDto.getUserName()))
            throw new BusinessException(HttpCodeEnum.USERNAME_EXIST);
        if (!nowUser.getEmail().equals(userDto.getEmail())&&emailExit(userDto.getEmail()))
            throw new BusinessException(HttpCodeEnum.EMAIL_EXIST);
        User user=BeanCopyUtil.copyBean(userDto,User.class);
        updateById(user);
        baseMapper.deleteUserRole(user.getId());
        baseMapper.insertUserRole(user.getId(),userDto.getRoleIds());
        return ResponseResult.success();
    }
}

