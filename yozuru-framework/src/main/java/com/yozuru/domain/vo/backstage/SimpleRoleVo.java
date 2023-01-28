package com.yozuru.domain.vo.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单的角色VO
 * @author :Yozuru
 * @since :2023/1/28 19:23
 */
@Data
@NoArgsConstructor
public class SimpleRoleVo {
    private Long id;
    private String roleName;
}
