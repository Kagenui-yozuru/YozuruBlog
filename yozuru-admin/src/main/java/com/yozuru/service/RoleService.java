package com.yozuru.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.RoleDto;
import com.yozuru.domain.entity.Role;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.RoleVo;
import com.yozuru.domain.vo.SimpleRoleVo;

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

    ResponseResult<PageVo<RoleVo>> getRoleListByPage(PageDto pageDto, String roleName, String status);

    ResponseResult<Object> updateStatus(Long roleId, String status);

    ResponseResult<Object> addRole(RoleDto roleDto);

    ResponseResult<RoleVo> getRoleById(Long id);

    ResponseResult<Object> updateRole(RoleDto roleDto);

    ResponseResult<Object> deleteRoleById(Long id);

    ResponseResult<List<SimpleRoleVo>> getAllRole();
}

