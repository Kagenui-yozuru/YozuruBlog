package com.yozuru.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@ApiModel("新增评论的数据传输对象")
public class AddCommentDto {

    @NotNull
    @ApiModelProperty("所评论的文章id")
    private Long articleId;

    @NotNull
    @ApiModelProperty("评论的分类，0为博客评论，1为留言板评论")
    private Integer type;

    @ApiModelProperty("根评论的id，若自身为根评论则为-1")
    private Long rootId;
    @ApiModelProperty("所回复的评论的id，若自身为根评论则为-1")
    private Long toCommentId;
    @ApiModelProperty("所回复的用户的id，若自身为根评论则为-1")
    private Long toCommentUserId;

    @NotNull
    @Length(min = 1,max = 300,message = "评论的长度应在300字以内")
    @ApiModelProperty("评论的内容，长度应控制在1~300")
    private String content;
}
