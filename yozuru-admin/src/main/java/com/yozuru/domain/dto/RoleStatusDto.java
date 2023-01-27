package com.yozuru.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yozuru
 * @since :2023/1/28 4:10
 */
@Data
@NoArgsConstructor
public class RoleStatusDto {
    private Long roleId;
    private String status;
}
