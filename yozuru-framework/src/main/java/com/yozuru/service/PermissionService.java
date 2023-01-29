package com.yozuru.service;

import org.springframework.stereotype.Service;

/**
 * @author Yozuru
 * @since 2022-11-29 17:45:18
 * &#064;description  权限判断
 */
@Service("ps")
public interface PermissionService {
    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return true:具有  false:不具有
     */
    boolean hasPermission(String... permission);
}