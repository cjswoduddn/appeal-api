package com.appeal.exception;

public class UnexpectedMethodArgumentNullPointerException extends BusinessException {
    public UnexpectedMethodArgumentNullPointerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
