package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.AddCommentDto;
import com.yozuru.domain.vo.CommentVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yozuru
 */
@Api(tags = "评论",value = "评论相关的接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @SystemLog(businessName = "获得评论列表")
    @ApiOperation("分页获取指定文章的评论列表")
    @GetMapping("/commentList")
    public ResponseResult<PageVo<CommentVo>> getCommentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.getCommentList(articleId, pageNum, pageSize);
    }

    @SystemLog(businessName = "添加评论")
    @ApiOperation("发布评论，需要传递token")
    @PostMapping
    public ResponseResult<Object> addComment(@RequestBody AddCommentDto addCommentDto){
        return commentService.addCommentList(addCommentDto);
    }
}
