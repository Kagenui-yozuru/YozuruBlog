package com.yozuru.domain.vo.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 展示用户列表的VO
 * @author :Yozuru
 * @since :2023/1/28 18:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Long id;
    private String userName;
    private String nickName;
    private String phonenumber;
    private String status;
    private Date createTime;
}
