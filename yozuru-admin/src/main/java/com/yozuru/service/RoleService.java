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

    List<String> selectRoleKeysByUserId(Long id);
}

