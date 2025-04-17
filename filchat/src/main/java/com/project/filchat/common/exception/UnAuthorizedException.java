package com.project.filchat.common.exception;

public class UnAuthorizedException extends BaseException {

    public UnAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
