package com.example.marketsystem.security;

public class SecurityConstants {
    public static final String[] WHITE_LIST = {
            "/user/login",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/swagger-resources/**",
            "/webjars/**",
            "/**"
    };
}
