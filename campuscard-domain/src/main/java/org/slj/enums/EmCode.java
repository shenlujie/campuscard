package org.slj.enums;

/**
*@Description: http相应状态码
*@create: 2019/3/20
*@Author: SLJ
*/
public enum EmCode {
    /**
     * 查询成功
     */
    SUCCESS(200,"查询成功"),

    /**
     * 请求错误
     */
    BAD_REQUEST(400,"请求错误"),

    /**
     * 未得到认证
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    /**
     * 没有权限
     */
    FORBIDDEN(403,"没有权限"),

    /**
     * 资源不存在
     */

    NOT_FOUND(404,"资源不存在"),

    /**
     * 内部错误
     */
    SERVER_ERROR(500,"内部错误");

    private Integer code;
    private String msg;

    EmCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
