package com.project.filchat.common.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
