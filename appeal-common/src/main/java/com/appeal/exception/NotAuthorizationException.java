package com.appeal.exception;

public class NotAuthorizationException extends BusinessException {
    public NotAuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
