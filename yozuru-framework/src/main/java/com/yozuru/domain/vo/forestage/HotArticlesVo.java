package com.yozuru.domain.vo.forestage;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 前台热门文章的VO
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class HotArticlesVo implements Serializable {
    private Long id;
    private String title;
    private Long viewCount;
}
