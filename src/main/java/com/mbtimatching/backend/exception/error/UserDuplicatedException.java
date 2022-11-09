package com.mbtimatching.backend.exception.error;

import com.mbtimatching.backend.exception.ErrorCode;

public class UserDuplicatedException extends RuntimeException {

    public UserDuplicatedException(){
        super(ErrorCode.USER_DUPLICATED.getMessage());
    }

    public UserDuplicatedException(Exception ex){
        super(ex);
    }
}
