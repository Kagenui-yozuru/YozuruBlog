package com.yozuru.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@ApiModel("前台登录的数据传输对象")
public class BlogLoginDto {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$")
    @ApiModelProperty("登录用户名,长度应为6~14字符。仅支持字母、数字、下划线")
    private String userName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,14}$")
    @ApiModelProperty("登录密码,长度应为6~14字符。仅支持字母、数字、下划线")
    private String password;
}
