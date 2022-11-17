package com.yozuru.domain.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yozuru
 */
@Getter
public enum HttpCodeEnum {

    SUCCESS(200, "成功"),
    NEED_LOGIN(401, "请登录后再操作"),
    NO_PERMISSION(403, "没有权限"),
    SYSTEM_ERROR(500, "系统异常");

    private final int code;
    private final String message;

    HttpCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
