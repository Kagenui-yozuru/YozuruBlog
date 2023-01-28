package com.yozuru.domain.dto.backstage;

import lombok.Data;

/**
 * 条件查询用户列表的dto
 * @author :Yozuru
 * @since :2023/1/28 18:34
 */
@Data
public class QueryUserDto {
    private String userName;
    private String phonenumber;
    private String status;
}
