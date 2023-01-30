package com.yozuru.domain.dto.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 添加、修改标签的dto
 * @author Yozuru
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    @NotNull(message = "修改的id不能为空",groups = Update.class)
    @Null(message = "添加的id必须为空",groups = Add.class)
    private Long id;
    @Length(max = 10, message = "标签名长度不能超过10", groups = {Add.class, Update.class})
    @NotBlank(message = "标签名不能为空", groups = {Add.class, Update.class})
    private String name;
    @Length(max = 10, message = "备注长度不能超过10", groups = {Add.class, Update.class})
    private String remark;

    public interface Add {
    }
    public interface Update {
    }
}


