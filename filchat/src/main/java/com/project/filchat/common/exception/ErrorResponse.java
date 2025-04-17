package com.project.filchat.common.exception;

public record ErrorResponse(
    int statusCode,
    String message
) {
}
