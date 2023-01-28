package com.yozuru.service.forestage.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.RedisConstant;
import com.yozuru.domain.dto.LoginDto;
import com.yozuru.domain.entity.LoginUser;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.forestage.UserLoginVo;
import com.yozuru.domain.vo.forestage.UserInfoVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.exception.SystemException;
import com.yozuru.service.forestage.BlogLoginService;
import com.yozuru.utils.BeanCopyUtil;
import com.yozuru.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * 前台登录服务实现类
 *
 * @author Yozuru
 * @since 2022-11-19 16:06:06
 */

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager manager;

    @CreateCache(area = "blogLogin", name = RedisConstant.BLOG_USER_KEY_PREFIX,cacheType = CacheType.REMOTE)
    private Cache<Long, LoginUser> userDetailCache;

    public ResponseResult<UserLoginVo> login(LoginDto loginDto) {
        //通过用户名和密码来生成本次验证的token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        //将本次验证的token传入AuthenticationManager，它将会帮我们进行验证
        Authentication authenticate=null;
        try {
            authenticate = manager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            throw new SystemException(HttpCodeEnum.LOGIN_ERROR);
        }
        //如果验证失败，返回的结果将是一个空值
        if (Objects.isNull(authenticate)) {
            throw new BusinessException(HttpCodeEnum.LOGIN_ERROR);
        }
        //如果验证成功，Authentication中将包含了查询到的用户详细信息。
        //Authentication中Principal变量储存的是UserDetailsService的查询结果。
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        //获得本次登录的用户id
        Long id = loginUser.getUser().getId();
        //把用户详细信息存入redis
        userDetailCache.put(id, loginUser);
        //生成id的jwt
        String jwt = JwtUtil.createJWT(id.toString());
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo userLoginVo = new UserLoginVo(jwt, userInfoVo);
        return ResponseResult.success(userLoginVo);
    }

    @Override
    public ResponseResult<Object> logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")){
            return ResponseResult.success();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        userDetailCache.remove(userId);
        return ResponseResult.success();
    }
}
