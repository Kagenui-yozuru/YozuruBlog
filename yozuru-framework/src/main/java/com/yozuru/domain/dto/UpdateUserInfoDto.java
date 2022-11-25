package com.yozuru.domain.dto;

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
    private String avatar;
    @Length(min = 4,max = 10,message = "昵称的长度应为4-10字符")
    private String nickName;
    @Range(min = 0,max = 1)
    @NotNull
    private Integer sex;
}
