package com.mbtimatching.backend.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String code;
    private String message;
    private int status;
}
