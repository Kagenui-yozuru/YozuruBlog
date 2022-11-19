package com.yozuru.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yozuru.mapper.CommentMapper;
import com.yozuru.domain.entity.Comment;
import com.yozuru.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author Yozuru
 * @since 2022-11-19 23:26:54
 */

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

