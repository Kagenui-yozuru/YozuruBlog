package com.yozuru.domain.vo.backstage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 后台菜单的VO
 * @author Yozuru
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor

public class MenuVo extends SimpleMenuVo {
    //菜单名称
    private String menuName;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;
    //组件路径
    private String component;
    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //菜单状态（0显示 1隐藏）
    private String visible;
    //菜单状态（0正常 1停用）
    private String status;
    //权限标识
    private String perms;
    //菜单图标
    private String icon;
    //创建时间
    private Date createTime;

}
