package com.mbtimatching.backend.exception.error;

import com.mbtimatching.backend.exception.ErrorCode;

public class MbtiApiCallFailException extends RuntimeException {
    public MbtiApiCallFailException(){
        super(ErrorCode.MBTI_API_FAILED.getMessage());
    }

    public MbtiApiCallFailException(Exception ex){
        super(ex);
    }
}
