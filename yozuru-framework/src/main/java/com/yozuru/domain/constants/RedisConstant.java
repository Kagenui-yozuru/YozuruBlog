package com.yozuru.domain.constants;

/**
 * @author Yozuru
 */

public class RedisConstant {
    /**
     * 前台已登录用户详细信息的Key前缀
     */
    public static final String BLOG_USER_KEY_PREFIX = "blogLogin_";
    /**
     * 后台已登录用户详细信息的Key前缀
     */
    public static final String ADMIN_USER_KEY_PREFIX = "adminLogin_";

    /**
     * 缓存文章浏览量的Key前缀
     */
    public static final String VIEW_COUNT_KEY_PREFIX="viewCount_";
}
