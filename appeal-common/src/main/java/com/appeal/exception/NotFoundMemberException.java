package com.appeal.exception;

public class NotFoundMemberException extends BusinessException {
    public NotFoundMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
