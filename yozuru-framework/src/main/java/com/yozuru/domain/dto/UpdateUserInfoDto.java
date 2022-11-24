package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class UpdateUserInfoDto {
    private String avatar;
    private String nickName;
    private Integer sex;
}
