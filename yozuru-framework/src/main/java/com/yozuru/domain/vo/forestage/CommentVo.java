package com.yozuru.domain.vo.forestage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 前台博文评论的VO
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    private Long id;
    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    //所回复的目标评论的username
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String username;
    //头像
    private String avatar;

    private List<CommentVo> children;

}
