package com.yozuru.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.constants.SystemConstant;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.dto.RoleDto;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.domain.vo.RoleVo;
import com.yozuru.domain.vo.SimpleMenuVo;
import com.yozuru.domain.vo.SimpleRoleVo;
import com.yozuru.mapper.RoleMapper;
import com.yozuru.domain.entity.Role;
import com.yozuru.service.RoleService;
import com.yozuru.utils.BeanCopyUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Object> updateRole(RoleDto roleDto) {
        Role role = BeanCopyUtil.copyBean(roleDto, Role.class);
        updateById(role);
        List<Long> menuIds = roleDto.getMenuIds();
        if (menuIds != null && menuIds.size() > 0) {
            baseMapper.deleteRoleMenuByRoleId(role.getId());
            baseMapper.insertRoleMenu(menuIds, role.getId());
        }
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> deleteRoleById(Long id) {
        if (id == SystemConstant.ADMIN_ID) {
            return ResponseResult.errorResult(HttpCodeEnum.CAN_NOT_DELETE_ADMIN);
        }
        baseMapper.deleteRoleMenuByRoleId(id);
        removeById(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<List<SimpleRoleVo>> getAllRole() {
        List<Role> roleList = list();
        List<SimpleRoleVo> simpleRoleVoList = BeanCopyUtil.copyBeanList(roleList, SimpleRoleVo.class);
        return ResponseResult.success(simpleRoleVoList);
    }

    @Override
    public ResponseResult<PageVo<RoleVo>> getRoleListByPage(PageDto pageDto, String roleName, String status) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(roleName),Role::getRoleName, roleName)
                .eq(Strings.isNotEmpty(status),Role::getStatus, status)
                .orderByAsc(Role::getRoleSort);
        Page<Role> pageObj = new Page<Role>(pageDto.getPageNum(), pageDto.getPageSize());
        pageObj=page(pageObj,queryWrapper);
        List<Role> roleList = pageObj.getRecords();
        List<RoleVo> roleVoList = BeanCopyUtil.copyBeanList(roleList, RoleVo.class);
        PageVo<RoleVo> pageVo = new PageVo<>(roleVoList, pageObj.getTotal());
        return ResponseResult.success(pageVo);
    }

    @Override

    public ResponseResult<Object> updateStatus(Long roleId, String status) {
        Role role = getById(roleId);
        if (Objects.isNull(role)) {
            return ResponseResult.errorResult(HttpCodeEnum.ROLE_NOT_EXIT);
        }
        role.setStatus(status);
        updateById(role);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult<Object> addRole(RoleDto roleDto) {
        Role role = BeanCopyUtil.copyBean(roleDto, Role.class);
        save(role);
        List<Long> menuIds = roleDto.getMenuIds();
        if (menuIds != null && menuIds.size() > 0) {
            baseMapper.insertRoleMenu(menuIds, role.getId());
        }
        return ResponseResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<RoleVo> getRoleById(Long id) {
        Role role = getById(id);
        if (Objects.isNull(role)) {
            return ResponseResult.errorResult(HttpCodeEnum.ROLE_NOT_EXIT);
        }
        RoleVo roleVo = BeanCopyUtil.copyBean(role, RoleVo.class);
        return ResponseResult.success(roleVo);
    }
}

