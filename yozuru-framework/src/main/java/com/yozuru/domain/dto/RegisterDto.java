package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class RegisterDto {
    @NotNull
    @Length(min = 6,max = 14,message = "用户名的长度应为6~14字符")
    private String userName;
    @NotNull
    @Length(min = 6,max = 14,message = "密码的长度应为6-14字符")
    private String password;
    @NotNull
    @Email
    private String email;
    @Length(min = 4,max = 10,message = "昵称的长度应为4-10字符")
    private String nickName;
}
