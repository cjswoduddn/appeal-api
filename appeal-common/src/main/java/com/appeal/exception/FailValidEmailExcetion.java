package com.appeal.exception;

public class FailValidEmailExcetion extends BusinessException {
    public FailValidEmailExcetion(ErrorCode errorCode) {
        super(errorCode);
    }
}
