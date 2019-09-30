package com.liutao.util;

/**
 * 返回信息封装
 * <只封装了编码和消息>
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/6
 * @Modified By:
 */
public class RestfulResponse {
    /**
     * 业务操作结果Code
     */
    protected int status;
    /**
     * 消息
     */
    protected String message;
    public RestfulResponse() {
    }

    public RestfulResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
