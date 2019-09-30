package com.liutao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtToken {
    public String access_token;
    public int expires_in;
    public String token_type;
}
