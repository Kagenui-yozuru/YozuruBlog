package com.yozuru.domain.dto.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改角色状态的dto
 * @author :Yozuru
 * @since :2023/1/28 4:10
 */
@Data
@NoArgsConstructor
public class RoleStatusDto {
    private Long roleId;
    private String status;
}
