package com.mbtimatching.backend.exception.error;

import com.mbtimatching.backend.exception.ErrorCode;

public class RegisterFailedException extends RuntimeException {

    public RegisterFailedException(){
        super(ErrorCode.REGISTER_FAILED.getMessage());
    }

    public RegisterFailedException(Exception ex){
        super(ex);
    }
}
