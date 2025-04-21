package com.project.filchat.infrastructure.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.project.filchat.common.exception.ErrorCode;
import com.project.filchat.common.exception.UnAuthorizedException;
import com.project.filchat.infrastructure.config.properties.JwtProperties;
import com.project.filchat.interfaces.auth.AuthDto;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    private final SecretKey secretKey;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;

    public JwtProvider(JwtProperties jwtProperties) {
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationTime = jwtProperties.accessTokenExpirationTime();
        this.refreshTokenExpirationTime = jwtProperties.refreshTokenExpirationTime();
    }

    public AuthDto.TokenResponse createJwtToken(String userToken, String nickname) {
        Date now = new Date();
        Date accessTokenExpiration = new Date(now.getTime() + accessTokenExpirationTime);
        Date refreshTokenExpiration = new Date(now.getTime() + refreshTokenExpirationTime);

        String accessToken = Jwts.builder()
            .signWith(secretKey, Jwts.SIG.HS256)
            .issuedAt(now)
            .expiration(accessTokenExpiration)
            .claim("userToken", userToken)
            .claim("nickname", nickname)
            .compact();

        String refreshToken = Jwts.builder()
            .signWith(secretKey, Jwts.SIG.HS256)
            .issuedAt(now)
            .expiration(refreshTokenExpiration)
            .claim("userToken", userToken)
            .claim("nickname", nickname)
            .compact();
        return new AuthDto.TokenResponse(userToken, accessToken, refreshToken);
    }

    public void validateToken(final String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new UnAuthorizedException(ErrorCode.INVALID_TOKEN);
        }
    }
}
