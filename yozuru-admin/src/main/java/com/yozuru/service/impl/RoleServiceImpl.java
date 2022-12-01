package com.yozuru.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.mapper.RoleMapper;
import com.yozuru.domain.entity.Role;
import com.yozuru.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-29 17:50:28
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeysByUserId(Long id) {
        List<String> roleKeys = new ArrayList<>();
        if (id == SystemConstant.ADMIN_ID) {
            roleKeys.add("admin");
            return roleKeys;
        }
        return baseMapper.selectRoleKeysByUserId(id);
    }
}

