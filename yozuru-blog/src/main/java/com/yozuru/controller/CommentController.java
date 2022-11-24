package com.yozuru.controller;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.AddCommentDto;
import com.yozuru.domain.vo.CommentVo;
import com.yozuru.domain.vo.PageVo;
import com.yozuru.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yozuru
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult<PageVo<CommentVo>> getCommentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.getCommentList(articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult<Object> addComment(@RequestBody AddCommentDto addCommentDto){
        return commentService.addCommentList(addCommentDto);
    }
}
