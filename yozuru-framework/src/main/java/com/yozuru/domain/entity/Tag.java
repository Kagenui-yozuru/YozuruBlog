package com.yozuru.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 标签(Tag)表实体类
 *
 * @author Yozuru
 * @since 2022-12-02 17:24:29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("yozuru_tag")
public class Tag  {
    
    private Long id;
    //标签名
    private String name;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //备注
    private String remark;

}

