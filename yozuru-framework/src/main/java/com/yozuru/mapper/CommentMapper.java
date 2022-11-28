package com.yozuru.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yozuru.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author Yozuru
 * @since 2022-11-19 23:26:54
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}

