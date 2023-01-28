package com.yozuru.domain.vo.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台处理标签对象的VO
 * @author Yozuru
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    private Long id;
    private String name;
    private String remark;
}
