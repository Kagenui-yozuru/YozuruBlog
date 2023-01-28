package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-12-02 17:24:29
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}

