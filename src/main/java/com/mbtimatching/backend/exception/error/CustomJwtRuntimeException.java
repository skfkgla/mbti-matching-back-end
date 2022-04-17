package com.mbtimatching.backend.exception.error;

import com.mbtimatching.backend.exception.ErrorCode;

public class CustomJwtRuntimeException extends RuntimeException {
        public CustomJwtRuntimeException(){
            super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
        }

        public CustomJwtRuntimeException(Exception ex){
            super(ex);
        }
}
