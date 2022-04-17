package com.mbtimatching.backend.exception.error;

import com.mbtimatching.backend.exception.ErrorCode;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException(){
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }

    public LoginFailedException(Exception ex){
        super(ex);
    }
}
