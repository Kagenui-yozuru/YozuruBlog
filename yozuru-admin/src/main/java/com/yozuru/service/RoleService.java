package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author Yozuru
 * @since 2022-11-29 17:50:28
 */

public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID查询该用户的角色信息
     * @param id 用户ID
     * @return 角色关键字列表
     */
    List<String> selectRoleKeysByUserId(Long id);
}

