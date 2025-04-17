package com.project.filchat.domain.auth;

public class AuthDto {

    public record LoginResponse(
        String userToken,
        String accessToken,
        String refreshToken
    ) {

    }
}
