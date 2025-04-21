package com.project.filchat.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // JWT
    INVALID_AUTH_HEADER("Authorization 헤더의 정보가 유효하지 않습니다."),
    EXPIRED_TOKEN("만료된 토큰입니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),

    // OAuth
    INVALID_AUTH("지원하지 않는 oauth입니다."),
    OAUTH_TOKEN_FETCH_FAILED("OAuth access token을 가져오는 데 실패했습니다."),
    USER_INFO_FETCH_FAILED("사용자 정보를 불러오는 데 실패했습니다.");

    private final String message;
}
