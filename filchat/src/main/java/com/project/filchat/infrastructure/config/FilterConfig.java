package com.project.filchat.infrastructure.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.filchat.infrastructure.jwt.JwtProvider;
import com.project.filchat.interfaces.filter.AuthExceptionHandlerFilter;
import com.project.filchat.interfaces.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final JwtProvider jwtProvider;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> jwtFilter = new FilterRegistrationBean<>();
        jwtFilter.setFilter(new JwtFilter(jwtProvider));
        jwtFilter.addUrlPatterns("/api/*");
        jwtFilter.setOrder(2);
        return jwtFilter;
    }

    @Bean
    public FilterRegistrationBean<AuthExceptionHandlerFilter> authExceptionHandlerFilter() {
        FilterRegistrationBean<AuthExceptionHandlerFilter> authExceptionHandlerFilter = new FilterRegistrationBean<>();
        authExceptionHandlerFilter.setFilter(new AuthExceptionHandlerFilter());
        authExceptionHandlerFilter.addUrlPatterns("/api/*");
        authExceptionHandlerFilter.setOrder(1);
        return authExceptionHandlerFilter;
    }
}
