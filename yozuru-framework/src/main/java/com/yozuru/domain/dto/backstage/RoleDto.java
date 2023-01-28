package com.yozuru.domain.dto.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 添加、修改角色的dto
 * @author :Yozuru
 * @since :2023/1/28 4:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private String remark;
    List<Long> menuIds;
}
