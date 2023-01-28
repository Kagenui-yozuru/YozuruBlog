package com.yozuru.domain.dto.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 后台管理员添加、修改用户的dto
 * @author :Yozuru
 * @since :2023/1/28 19:41
 */
@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String password;

    private String nickName;
    private String phonenumber;
    private String email;
    private String sex;
    private Integer status;

    private List<Long> roleIds;

}
