package com.project.filchat.infrastructure.jwt;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.filchat.common.exception.ErrorCode;
import com.project.filchat.common.exception.UnAuthorizedException;
import com.project.filchat.domain.auth.AuthDto;
import com.project.filchat.infrastructure.config.properties.JwtProperties;

class JwtProviderTest {
    private final String secretKey = "2901ujr9021urf0u902hf021y90fh9c210hg093";
    private final JwtProvider jwtProvider = new JwtProvider(
        new JwtProperties(secretKey, 10000, 10000));

    @Test
    @DisplayName("엑세스 토큰, 리프레시 토큰 생성에 성공한다.")
    void whenCreateAccessTokenAndRefreshToken_thenSuccess() {
        // given

        // when
        AuthDto.LoginResponse jwtTokens = jwtProvider.createJwtToken("userToken", "nickname");

        // then
        assertThat(jwtTokens.accessToken()).isNotBlank();
        assertThat(jwtTokens.refreshToken()).isNotBlank();
    }

    @Test
    @DisplayName("잘못된 토큰이 들어오면 예외를 던진다.")
    void whenInvalidToken_thenThrowsException() {
        // given
        String invalidToken = "qwe.123.qwee22";

        // when & then
        assertThatThrownBy(
            () -> jwtProvider.validateToken(invalidToken)).isInstanceOf(UnAuthorizedException.class)
            .extracting("ErrorCode").isEqualTo(ErrorCode.INVALID_TOKEN);
    }
}
