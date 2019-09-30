package com.liutao.util;


/**
 * 返回数据封装
 *
 * @author: LIUTAO
 * @Description:
 * @Date: Created in 14:23 2018/6/6
 * @Modified By:
 */
public class RestfulDataResponse<T> extends RestfulResponse
{
    public RestfulDataResponse()
    {
    }

    public RestfulDataResponse(int code, String message, T data)
    {
        super(code, message);
        this.data = data;
    }

    /**
     * 实体类型
     */
    public T data;

    @Override
    public String toString() {
        return "RestfulDataResponse{" +
                "data=" + data +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
