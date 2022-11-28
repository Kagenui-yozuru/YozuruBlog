package com.yozuru.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class RegisterDto {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$",message = "长度应为6~14字符。且不能包含特殊符号")
    @ApiModelProperty("注册账户的用户名,长度应为6~14字符。仅支持字母、数字、下划线")
    private String userName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$",message = "长度应为6~14字符。且不能包含特殊符号")
    @ApiModelProperty("注册账户的密码,长度应为6~14字符。仅支持字母、数字、下划线")
    private String password;

    @NotNull
    @Email(message = "请输入正确的邮箱")
    @ApiModelProperty("注册账户的邮箱")
    private String email;

    @Length(min = 4,max = 10,message = "昵称的长度应为4-10字符")
    private String nickName;
}
