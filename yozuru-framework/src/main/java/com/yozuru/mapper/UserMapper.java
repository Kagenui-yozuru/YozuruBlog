package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-19 16:58:31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

