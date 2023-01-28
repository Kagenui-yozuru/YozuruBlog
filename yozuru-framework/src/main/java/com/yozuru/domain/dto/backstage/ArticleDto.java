package com.yozuru.domain.dto.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.util.List;

/**
 * 后台对文章修改、添加、展示的数据传输对象
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class ArticleDto {
    @Null(message = "添加操作文章id必须为空", groups = {Add.class})
    @NotNull(message = "修改操作文章id不能为空", groups = {Update.class})
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
    @Pattern(regexp = "^http://rninhqsaw.hb-bkt.clouddn.com",message = "图片地址格式不正确")
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

    //继承Default分组，确保Add和Update分组包含Default分组
    public interface Add extends Default {
    }
    public interface Update extends Default {
    }
}
