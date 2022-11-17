package com.yozuru.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class HotArticlesVo {
    private Long id;
    private String title;
    private Long viewCount;
}
