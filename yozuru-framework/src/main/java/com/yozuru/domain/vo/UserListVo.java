package com.yozuru.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author :Yozuru
 * @since :2023/1/28 18:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListVo {
    private Long id;
    private String userName;
    private String nickName;
    private String phonenumber;
    private String status;
    private Date createTime;
}
