package com.yozuru.domain.dto.backstage;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

/**
 * @author :Yozuru
 * @since :2023/1/29 16:10
 */
@Data
@NoArgsConstructor
public class CategoryDto {
    @NotNull(groups = {Update.class},message = "更新操作必须传递id")
    @Null(groups = {Add.class},message = "新增操作不能传递id")
    private Long id;
    @Length(min = 1,max = 20,message = "分类名称长度应在1~5字以内")
    private String name;
    @Length(min = 1,max = 20,message = "分类描述应在1~20字以内")
    private String description;
    private Integer status;

    public interface Add extends Default {};
    public interface Update extends Default {};
}
