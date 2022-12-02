package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    //标题
    @NotBlank(message = "文章标题不能为空")
    @Length(max = 50, message = "文章标题不能超过50个字符")
    private String title;
    //文章内容
    @NotBlank(message = "文章内容不能为空")
    @Length(max = 10000, message = "文章内容不能超过10000个字符")
    private String content;
    //文章摘要
    @Length(max = 200, message = "文章摘要不能超过200个字符")
    private String summary;
    //所属分类id
    @NotNull(message = "所属分类不能为空")
    private Long categoryId;
    //缩略图
    @Pattern(regexp = "^http://rlv1iif22.hb-bkt.clouddn.com",message = "图片地址格式不正确")
    private String thumbnail;
    //是否置顶（0否，1是）
    @Pattern(regexp = "[0,1]",message = "置顶状态不正确")
    private String isTop;
    //状态（0已发布，1草稿）
    @Pattern(regexp = "[0,1]",message = "状态不正确")
    private String status;
    //是否允许评论 1是，0否
    @Pattern(regexp = "[0,1]",message = "评论状态不正确")
    private String isComment;
    private List<Long> tags;
}
