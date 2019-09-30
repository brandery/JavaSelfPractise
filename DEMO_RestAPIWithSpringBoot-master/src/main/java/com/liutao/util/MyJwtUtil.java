package com.liutao.util;

import org.springframework.util.StringUtils;

public class MyJwtUtil {
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    /**
     * 获取原始令牌值
     * @param authWithHeader
     * @return
     */
    public static String getRawToken(String authWithHeader) {
        return authWithHeader.substring(AUTHORIZATION_HEADER_PREFIX.length());
    }
    /**
     * 获取令牌
     * @param rawToken
     * @return
     */
    public static String getTokenHeader(String rawToken) {
        return AUTHORIZATION_HEADER_PREFIX + rawToken;
    }
    /**
     * 验证授权请求头
     * @param authWithHeader
     * @return
     */
    public static boolean validate(String authWithHeader) {
        //isNotNullAndNotWhiteSpace
        return StringUtils.hasText(authWithHeader) && authWithHeader.startsWith(AUTHORIZATION_HEADER_PREFIX);
    }
    /**
     * 获取授权头前缀
     * @return
     */
    public static String getAuthHeaderPrefix() {
        return AUTHORIZATION_HEADER_PREFIX;
    }
}
