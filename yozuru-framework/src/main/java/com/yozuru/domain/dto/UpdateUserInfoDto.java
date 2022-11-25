package com.yozuru.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class UpdateUserInfoDto {
    @Pattern(regexp = "^http://rlv1iif22.hb-bkt.clouddn.com")
    @ApiModelProperty("用户上传的头像的url")
    private String avatar;

    @Length(min = 4,max = 10,message = "昵称的长度应为4-10字符")
    private String nickName;

    @Pattern(regexp = "[0,1]",message = "虽然你的性别可以很自由，但是这里还是请你在男和女里选一个。")
    @NotNull
    private Integer sex;
}
