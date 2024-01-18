package com.example.marketsystem.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.TYPE,ElementType.TYPE_PARAMETER})
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
