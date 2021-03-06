package com.liutao.config;

import com.liutao.filters.MyJwtAuthFilter;
import com.liutao.filters.MyJwtLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 通过SpringSecurity的配置，将MyJwtLoginFilter，MyJwtAuthFilter组合在一起
 *
 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) 在springboot1.5.8的时候该注解是可以用的 具体看源码
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //自定义 默认
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/user/login", "/login", "/oauth/authorize").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestMatchers().antMatchers("/user/login", "/login", "/oauth/authorize")
                .and()
                .addFilter(new MyJwtLoginFilter(authenticationManager()))//登录过滤器
                .addFilter(new MyJwtAuthFilter(authenticationManager()));//自定义过滤器
    }

}