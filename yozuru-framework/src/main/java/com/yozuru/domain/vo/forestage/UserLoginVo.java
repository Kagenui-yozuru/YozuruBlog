package com.yozuru.domain.vo.forestage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前台用户登录后返回给前端的信息
 * @author Yozuru
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVo {
    private String token;
    private UserInfoVo userInfo;
}