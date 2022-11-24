package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class AddCommentDto {
    private Long articleId;
    private Integer type;
    private Long rootId;
    private Long toCommentId;
    private Long toCommentUserId;
    private String content;
}
