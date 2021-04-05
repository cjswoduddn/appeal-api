package com.appeal.exception;

public class NotSupportDtoException extends BusinessException {
    public NotSupportDtoException(ErrorCode errorCode) {
        super(errorCode);
    }
}
