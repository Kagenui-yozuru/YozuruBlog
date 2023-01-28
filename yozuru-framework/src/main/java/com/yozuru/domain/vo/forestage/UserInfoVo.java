package com.yozuru.domain.vo.forestage;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 前台展示用户基本信息的VO
 * @author Yozuru
 */

@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private Integer sex;

    private String email;

}
