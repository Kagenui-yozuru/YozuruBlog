package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Menu;
import com.yozuru.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-29 17:50:28
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleKeysByUserId(@Param("id") Long id);
    boolean insertRoleMenu(@Param("menuIds") List<Long> menuIds, @Param("roleId") Long roleId);

    boolean deleteRoleMenuByRoleId(@Param("roleId") Long roleId);

}

