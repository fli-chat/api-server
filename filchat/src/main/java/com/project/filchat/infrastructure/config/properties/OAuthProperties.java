package com.project.filchat.infrastructure.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth")
public record OAuthProperties(
    Kakao kakao
) {
    public record Kakao(
        ServerInfo serverInfo,
        String clientId,
        String secretId
    ) {
    }

    public record ServerInfo(
        String authTokenUrl,
        String resourceServerUrl,
        String redirectUrl
    ) {
    }
}
