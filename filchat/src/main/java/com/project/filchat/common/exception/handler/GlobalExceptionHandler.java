package com.project.filchat.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.filchat.common.exception.ErrorResponse;
import com.project.filchat.common.exception.UnAuthorizedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedException(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getErrorCode().getMessage()));
    }
}
