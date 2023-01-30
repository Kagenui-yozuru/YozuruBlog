package com.yozuru.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 前、后台登录的数据传输对象
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@ApiModel("前、后台登录的数据传输对象")
public class LoginDto {
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$", message = "用户名格式不正确")
    @ApiModelProperty("登录用户名,长度应为6~14字符。仅支持字母、数字、下划线")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$", message = "密码格式不正确")
    @ApiModelProperty("登录密码,长度应为6~14字符。仅支持字母、数字、下划线")
    private String password;
}
