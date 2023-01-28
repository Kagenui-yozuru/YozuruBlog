package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.LoginDto;
import com.yozuru.domain.entity.LoginUser;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.backstage.AdminInfoVo;
import com.yozuru.domain.vo.backstage.MenuVo;
import com.yozuru.domain.vo.backstage.RoutersVo;
import com.yozuru.domain.vo.forestage.UserInfoVo;
import com.yozuru.exception.BusinessException;
import com.yozuru.service.backstage.AdminLoginService;
import com.yozuru.service.backstage.MenuService;
import com.yozuru.service.backstage.RoleService;
import com.yozuru.utils.BeanCopyUtil;
import com.yozuru.utils.SecurityUtils;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Yozuru
 */
@RestController
@Api(tags = "登录",value = "后台用户登录相关的接口")
public class AdminLoginController {
    @Autowired
    private AdminLoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @SystemLog(businessName = "管理员用户登录")
    @ApiOperation("管理员用户登录")
    @PostMapping("/admin/login")
    public ResponseResult<Map<String,String>> login(@Validated @RequestBody LoginDto loginDto){
        if (!Strings.hasText(loginDto.getUserName())){
            throw new BusinessException(HttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(loginDto);
    }
    @SystemLog(businessName = "管理员用户登出")
    @ApiOperation("管理员用户登出")
    @PostMapping("/admin/logout")
    public ResponseResult<Object> logout(){
        return loginService.loginOut();
    }
    @GetMapping("/getInfo")
    @SystemLog(businessName = "获取管理员用户信息")
    @ApiOperation("获取该管理员用户信息(基本信息、权限信息、角色信息)")
    public ResponseResult<AdminInfoVo> getInfo(){
        //获取当前登录用户的信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询用户的权限信息
        List<String> perms= menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id获得角色信息身份信息
        List<String> roleKeys = roleService.selectRoleKeysByUserId(loginUser.getUser().getId());
        //封装返回的数据
        UserInfoVo user = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminInfoVo adminInfoVo = new AdminInfoVo(user,perms,roleKeys);
        return ResponseResult.success(adminInfoVo);
    }

    @GetMapping("/getRouters")
    @SystemLog(businessName = "获取管理员用户的路由信息")
    @ApiOperation("获取该管理员用户的路由信息")
    public ResponseResult<RoutersVo> getRouters(){
        //获取当前登录用户的id
        Long id = SecurityUtils.getUserId();
        //根据用户id查询用户的菜单信息
        List<MenuVo> menus = menuService.selectRouterMenuTreeByUserId(id);
        //封装返回的数据
        return ResponseResult.success(new RoutersVo(menus));
    }
}
