package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class NotAuthorizationException extends BusinessException {
    public NotAuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
