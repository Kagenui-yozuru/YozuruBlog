package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.entity.LoginUser;
import com.yozuru.domain.entity.User;
import com.yozuru.mapper.MenuMapper;
import com.yozuru.mapper.UserMapper;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Yozuru
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * @param username 请求登录的用户名
     * @return UserDetails
     * &#064;Description  这个方法后续会被AuthenticationManager调用，入参也由AuthenticationManager提供。
     * 作用是根据username来查询出用户详细信息返回。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //如果没有查到用户，就抛出异常。
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        //如果查到了用户，就封装成UserDetails返回,之后的校验逻辑不需要我们去写。
        //如果是后台账号（type为1），就查询出用户的权限信息，然后封装到LoginUser中。
        if (user.getType().equals(SystemConstant.USER_TYPE_ADMIN)){
            //根据用户id查询用户的权限信息
            List<String> perms = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,perms);
        }else {
            //如果是前台账号（type为0），就直接返回LoginUser。
            return new LoginUser(user, null);
        }
    }
}
