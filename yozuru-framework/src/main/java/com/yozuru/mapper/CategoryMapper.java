package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-18 00:34:47
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

