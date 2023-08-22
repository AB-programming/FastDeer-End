package com.deer.fastdeerend.common;

/**
 * 响应状态码集合
 *
 * @author AB-style
 * @date 2023/06/28
 */
public enum HttpResponseStatusCodeSet {
    /**
     * 请求成功
     */
    OK("200"),
    /**
     * 客户端错误，服务端无法理解请求
     */
    BadRequest("400"),
    /**
     * 身份未授权
     */
    Unauthorized("401"),
    /**
     * 服务端拒绝该请求
     */
    Forbidden("403"),
    /**
     * 服务器找不到资源
     */
    NotFound("404"),
    /**
     * 服务器内部错误
     */
    InternalServerError("500"),
    /**
     * 服务器不支持该功能
     */
    NotImplemented("501"),
    /**
     * 系统超载，服务不可用
     */
    ServiceUnavailable("503");

    private final String value;

    HttpResponseStatusCodeSet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
