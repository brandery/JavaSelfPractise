package com.liutao.util;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * Created by Zhao to get a httpclient instance which only accept utf-8 charset
 */
public class MyHttpClient
{
    public static RestTemplate getInstance()
    {
        RestTemplate restClient = new RestTemplate();
        StringHttpMessageConverter utf8StringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        utf8StringConverter.setWriteAcceptCharset(false);
        restClient.getMessageConverters().set(1, utf8StringConverter);

        return restClient;
    }
}
