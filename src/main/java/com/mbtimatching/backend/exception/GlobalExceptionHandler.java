package com.mbtimatching.backend.exception;

import com.mbtimatching.backend.exception.error.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Bean Validation에 실패했을 때, 에러메시지를 내보내기 위한 Exception Handler
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleParamViolationException(BindException ex) {
        // 파라미터 validation에 걸렸을 경우

        ErrorResponse response = ErrorResponse.builder()
                .status(ErrorCode.REQUEST_PARAMETER_BIND_FAILED.getStatus().value())
                .message(ErrorCode.REQUEST_PARAMETER_BIND_FAILED.getMessage())
                .code(ErrorCode.REQUEST_PARAMETER_BIND_FAILED.getCode())
                .build();
        return new ResponseEntity<>(response, ErrorCode.REQUEST_PARAMETER_BIND_FAILED.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        // 없는 경로로 요청 시

        ErrorResponse response = ErrorResponse.builder()
                .message(ErrorCode.NOT_FOUND_PATH.getMessage())
                .status(ErrorCode.NOT_FOUND_PATH.getStatus().value())
                .code(ErrorCode.NOT_FOUND_PATH.getCode())
                .build();

        return new ResponseEntity<>(response, ErrorCode.NOT_FOUND_PATH.getStatus());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        // GET POST 방식이 잘못된 경우

        ErrorResponse response = ErrorResponse.builder()
                .message(ErrorCode.METHOD_NOT_ALLOWED.getMessage())
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .code(ErrorCode.METHOD_NOT_ALLOWED.getCode())
                .build();

        return new ResponseEntity<>(response, ErrorCode.METHOD_NOT_ALLOWED.getStatus());
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {

        ErrorResponse response = ErrorResponse.builder()
                .message(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage())
                .status(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getStatus().value())
                .code(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode())
                .build();

        return new ResponseEntity<>(response, ErrorCode.UNSUPPORTED_MEDIA_TYPE.getStatus());
    }
    @ExceptionHandler(CustomJwtRuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleJwtException(CustomJwtRuntimeException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.INVALID_JWT_TOKEN.getCode())
                .message(ErrorCode.INVALID_JWT_TOKEN.getMessage())
                .status(ErrorCode.INVALID_JWT_TOKEN.getStatus().value())
                .build();

        return new ResponseEntity<>(response, ErrorCode.INVALID_JWT_TOKEN.getStatus());
    }
    @ExceptionHandler(LoginFailedException.class)
    protected ResponseEntity<ErrorResponse> handleLoginFailedException(LoginFailedException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.LOGIN_FAILED.getCode())
                .message(ErrorCode.LOGIN_FAILED.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus().value())
                .build();

        return new ResponseEntity<>(response, ErrorCode.LOGIN_FAILED.getStatus());
    }
    @ExceptionHandler(MbtiApiCallFailException.class)
    protected ResponseEntity<ErrorResponse> handleMbtiApiCallFailException(MbtiApiCallFailException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.MBTI_API_FAILED.getCode())
                .message(ErrorCode.MBTI_API_FAILED.getMessage())
                .status(ErrorCode.MBTI_API_FAILED.getStatus().value())
                .build();

        return new ResponseEntity<>(response, ErrorCode.MBTI_API_FAILED.getStatus());
    }
    @ExceptionHandler(RegisterFailedException.class)
    protected ResponseEntity<ErrorResponse> handleRegisterFailedException(RegisterFailedException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.REGISTER_FAILED.getCode())
                .message(ErrorCode.REGISTER_FAILED.getMessage())
                .status(ErrorCode.REGISTER_FAILED.getStatus().value())
                .build();

        return new ResponseEntity<>(response, ErrorCode.REGISTER_FAILED.getStatus());
    }
    @ExceptionHandler(UserDuplicatedException.class)
    protected ResponseEntity<ErrorResponse> handleUserDuplicatedException(UserDuplicatedException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.USER_DUPLICATED.getCode())
                .message(ErrorCode.USER_DUPLICATED.getMessage())
                .status(ErrorCode.USER_DUPLICATED.getStatus().value())
                .build();

        return new ResponseEntity<>(response, ErrorCode.USER_DUPLICATED.getStatus());
    }
}

