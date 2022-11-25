package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class AddCommentDto {
    @NotNull
    private Long articleId;
    @NotNull
    private Integer type;
    private Long rootId;
    private Long toCommentId;
    private Long toCommentUserId;
    @NotNull
    @Length(min = 1,max = 300,message = "评论的长度应在300字以内")
    private String content;
}
