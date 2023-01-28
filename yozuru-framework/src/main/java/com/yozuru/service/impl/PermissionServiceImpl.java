package com.yozuru.service.impl;

import com.yozuru.utils.SecurityUtils;

import java.util.List;

/**
 * @author Yozuru
 */

public class PermissionServiceImpl {
    public boolean hasPermission(String permission){
        //如果是超级管理员  直接返回true
        if(SecurityUtils.isAdmin()){
            return true;
        }
        //否则  获取当前登录用户所具有的权限列表 如何判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
