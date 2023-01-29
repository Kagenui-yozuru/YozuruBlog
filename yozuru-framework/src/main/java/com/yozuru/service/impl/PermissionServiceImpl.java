package com.yozuru.service.impl;

import com.yozuru.service.PermissionService;
import com.yozuru.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Yozuru
 */
@Service("ps")
public class PermissionServiceImpl implements PermissionService {
    public boolean hasPermission(String... permission){
        //如果是超级管理员  直接返回true
        if(SecurityUtils.isAdmin()){
            return true;
        }
        AtomicBoolean flag = new AtomicBoolean(false);
        //否则  获取当前登录用户所具有的权限列表 如何判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();

        Arrays.stream(permission).forEach(p -> {
            if(permissions.contains(p)){
                flag.set(true);
            }
        });
        return flag.get();
    }
}
