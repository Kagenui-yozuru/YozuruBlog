package com.yozuru.domain.constants;

/**
 * @author Yozuru
 */

public class SystemConstant {
    /**
     * 表示文章为已发布状态
     */
    public static final Integer ARTICLES_STATUS_NORMAL = 0;
    /**
     * 表示文章为编辑状态
     */
    public static final Integer ARTICLES_STATUS_DRAFT = 1;
    /**
     * 表示热门文章的展示条数
     */
    public static final Integer HOT_ARTICLES_LIST_SIZE = 10;
    /**
     * 表示分组为正常状态
     */
    public static final Integer STATUS_NORMAL = 0;
    /**
     * 表示分组为禁用状态
     */
    public static final Integer STATUS_FORBIDDEN = 1;
    /**
     * 表示该评论为根评论
     */
    public static final Integer COMMENT_ROOT_ID=-1;
    /**
     * 表示该评论为文章评论
     */
    public static final Integer COMMENT_TYPE_ARTICLE=0;
    /**
     * 超极管理员id
     */
    public static final long ADMIN_ID=1L;
    public static final String MENU="C";
    public static final String INDEX="M";
    public static final String BUTTON="F";
}
