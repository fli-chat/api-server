package com.project.filchat.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.project.filchat.infrastructure.config.properties.JwtProperties;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {
}
