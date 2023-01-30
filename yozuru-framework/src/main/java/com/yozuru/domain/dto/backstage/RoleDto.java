package com.yozuru.domain.dto.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import javax.validation.groups.Default;
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
    @NotNull(groups = {Update.class},message = "更新操作必须传递id")
    @Null(groups = {Add.class},message = "新增操作不能传递id")
    private Long id;
    @Length(min = 1,max = 8,message = "角色名称长度应在1~20字以内")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @Length(min = 1,max = 5,message = "角色权限字符串长度应在1~20字以内")
    @NotBlank(message = "角色权限字符串不能为空")
    private String roleKey;
    @Min(value = 0,message = "角色排序不能小于1")
    @Max(value = 100,message = "角色排序不能大于100")
    @NotNull(message = "角色排序不能为空")
    private Integer roleSort;
    private String status;
    private String remark;
    List<Long> menuIds;

    public interface Add extends Default {};
    public interface Update extends Default {};
}
