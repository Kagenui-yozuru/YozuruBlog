package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-19 16:58:31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    boolean insertUserRole(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    boolean deleteUserRole(Long id);

    List<Long> getRoleIdsByUserId(Long id);
}

