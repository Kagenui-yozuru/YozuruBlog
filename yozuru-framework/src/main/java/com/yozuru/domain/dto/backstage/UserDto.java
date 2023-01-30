package com.yozuru.domain.dto.backstage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.List;

/**
 * 后台管理员添加、修改用户的dto
 * @author :Yozuru
 * @since :2023/1/28 19:41
 */
@Data
@NoArgsConstructor
public class UserDto {
    @NotNull(groups = {Update.class},message = "更新操作用户id不能为空")
    @Null(groups = {Add.class},message = "新增操作不能传递id")
    private Long id;

    @NotNull(groups = {Add.class},message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$",message = "用户名长度应为6~14字符。且不能包含特殊符号")
    private String userName;
    @NotNull(groups = {Add.class},message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$",message = "密码长度应为6~14字符。且不能包含特殊符号")
    private String password;
    @NotEmpty(message = "昵称不能为空")
    @Length(min = 3,max = 10,message = "昵称的长度应为3-10字符")
    private String nickName;
    private String phonenumber;
    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "请输入正确的邮箱")
    private String email;
    private String sex;
    private Integer status;
    @NotEmpty(message = "至少选择一个角色")
    private List<Long> roleIds;

    public interface Update extends Default {}
    public interface Add extends Default {}
}
