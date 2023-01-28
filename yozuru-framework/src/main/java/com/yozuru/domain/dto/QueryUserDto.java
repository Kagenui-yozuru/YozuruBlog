package com.yozuru.domain.dto;

import lombok.Data;

/**
 * @author :Yozuru
 * @since :2023/1/28 18:34
 */
@Data
public class QueryUserDto {
    private String userName;
    private String phonenumber;
    private String status;
}
