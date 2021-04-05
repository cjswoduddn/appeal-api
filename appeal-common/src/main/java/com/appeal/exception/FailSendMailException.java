package com.appeal.exception;

public class FailSendMailException extends BusinessException {

    public FailSendMailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
