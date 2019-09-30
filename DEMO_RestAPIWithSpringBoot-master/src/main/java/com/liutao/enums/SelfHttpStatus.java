package com.liutao.enums;

/**
 * 自定义响应状态码
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/30  14:02
 * @Modified By:
 */
public enum SelfHttpStatus {
    PARAMS_IS_NULL(10001,"Parameter is null"),
    PARAMS_NOT_COMPLETE(10004,"Parameter is invalid")
    ;
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    SelfHttpStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
