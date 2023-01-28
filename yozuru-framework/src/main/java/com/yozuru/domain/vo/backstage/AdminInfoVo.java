package com.yozuru.domain.vo.backstage;


import com.yozuru.domain.vo.forestage.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 后台管理员登录后传递给前端的用户信息
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
