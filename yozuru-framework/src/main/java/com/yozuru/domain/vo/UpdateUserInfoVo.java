package com.yozuru.domain.vo;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yozuru
 * @since :2023/1/28 20:21
 */
@Data
@NoArgsConstructor
public class UpdateUserInfoVo {
    private Long id;
    private String userName;
    private String email;
    private String nickName;
    private String sex;
    private Integer status;

}
