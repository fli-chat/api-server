package com.project.filchat.infrastructure.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String secretKey,
    long accessTokenExpirationTime,
    long refreshTokenExpirationTime
) {
}
