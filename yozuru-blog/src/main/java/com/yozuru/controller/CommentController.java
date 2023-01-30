package com.yozuru.controller;

import com.yozuru.annotation.SystemLog;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.forestage.AddCommentDto;
import com.yozuru.domain.dto.PageDto;
import com.yozuru.domain.vo.forestage.CommentVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.forestage.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public ResponseResult<PageVo<CommentVo>> getCommentList(Long articleId,@Validated PageDto pageDto){
        return commentService.getCommentList(articleId,pageDto);
    }

    @SystemLog(businessName = "添加评论")
    @ApiOperation("发布评论，需要传递token")
    @PostMapping
    public ResponseResult<Object> addComment(@RequestBody @Validated AddCommentDto addCommentDto){
        return commentService.addCommentList(addCommentDto);
    }
}
