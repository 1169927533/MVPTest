package com.example.mvptest.util.okhttp分装;

import java.util.Map;

/**

        * 请求数据封装方式
        */

public interface IRequest {
    String POST = "POST";
    String GET = "GET";

    /**
     * 请求方式
     *
     * @param method
     */
    void setMethod(String method);

    /**
     * 请求头
     *
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

    /**
     * 请求体
     *
     * @param key
     * @param value
     */
    void setBody(String key, String value);

    /**
     * 执行URL
     *
     * @return
     */
    String getUrl();
    /**
     *获取请求头部
     */
    Map<String,String> getHeader();

    /**
     * 提供执行库请求参数
     */

    Object getBody();
}