package com.project.filchat.interfaces.auth;

import com.project.filchat.domain.auth.OAuthProvider;
import com.project.filchat.domain.user.User;

public class AuthDto {

    public record LoginRequest(
        OAuthProvider provider,
        String identifier,
        User.Gender gender,
        int ageGroup
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
