package com.project.filchat.interfaces.auth;

public class AuthDto {

    public record LoginRequest(
        String code
    ) {
    }

    public record TokenResponse(
        String userToken,
        String accessToken,
        String refreshToken
    ) {
    }

    public record LoginResponse(
        String userToken,
        String accessToken,
        String refreshToken,
        boolean isNew
    ) {
        public LoginResponse(TokenResponse response, boolean isNew) {
            this(response.userToken(), response.accessToken(), response.refreshToken(), isNew);
        }
    }
}
