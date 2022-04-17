package com.mbtimatching.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public enum ErrorCode {
    NOT_FOUND_PATH(HttpStatus.NOT_FOUND, "PATH_001", "NOT FOUND PATH", LocalDateTime.now()), // 없는 경로로 요청보낸 경우
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"PATH_002","METHOD NOT ALLOWED", LocalDateTime.now()), // POST GET방식 잘못 보낸경우
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "PATH_003", "UNSUPPORTED MEDIA TYPE", LocalDateTime.now()),

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH_001", " AUTHENTICATION_FAILED.", LocalDateTime.now()),
    LOGIN_FAILED(HttpStatus.NOT_FOUND, "AUTH_002", " LOGIN_FAILED.", LocalDateTime.now()),
    REGISTER_FAILED(HttpStatus.FORBIDDEN, "AUTH_003", "REGISTER_FAILED", LocalDateTime.now()),
    REQUEST_PARAMETER_BIND_FAILED(HttpStatus.BAD_REQUEST, "REQ_001", "PARAMETER_BIND_FAILED", LocalDateTime.now()),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH004", "INVALID_JWT_TOKEN.", LocalDateTime.now()),
    MBTI_API_FAILED(HttpStatus.FORBIDDEN, "MBTI_001", "mbti api call failed", LocalDateTime.now());

    private final String code;
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    ErrorCode(final HttpStatus status, final String code, final String message, final LocalDateTime timestamp){
        this.status = status;
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
}
