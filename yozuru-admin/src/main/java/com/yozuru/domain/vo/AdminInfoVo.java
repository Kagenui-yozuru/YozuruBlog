package com.yozuru.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoVo {
    //该管理员账号的基本信息
    private UserInfoVo user;
    //该管理员账号的权限信息
    private List<String> permissions;
    //该管理员账号的角色信息(角色的名称)
    private List<String> roles;
}
