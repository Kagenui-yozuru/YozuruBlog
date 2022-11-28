package com.yozuru.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.RedisConstant;
import com.yozuru.domain.dto.BlogLoginDto;
import com.yozuru.domain.entity.LoginUser;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.exception.BusinessException;
import com.yozuru.exception.SystemException;
import com.yozuru.service.AdminLoginService;
import com.yozuru.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yozuru
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AuthenticationManager manager;

    @CreateCache(area = "adminLogin", name = RedisConstant.ADMIN_USER_KEY_PREFIX,cacheType = CacheType.REMOTE)
    private Cache<Long, LoginUser> adminDetailCache;

    public ResponseResult<Map<String,String>> login(BlogLoginDto loginDto) {
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
        adminDetailCache.put(id, loginUser);
        //生成id的jwt
        String jwt = JwtUtil.createJWT(id.toString());
        Map<String,String> result = new HashMap<>();
        result.put("token",jwt);
        return ResponseResult.success(result);
    }
}
