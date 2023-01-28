package com.yozuru.domain.vo.backstage;

import com.yozuru.domain.vo.backstage.SimpleRoleVo;
import com.yozuru.domain.vo.backstage.UpdateUserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 后台更新用户的VO
 * @author :Yozuru
 * @since :2023/1/28 20:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserVo {
    private UpdateUserInfoVo user;
    private List<Long> roleIds;
    private List<SimpleRoleVo> roles;
}
