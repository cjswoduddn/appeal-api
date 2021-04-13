package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class NotFoundMemberException extends BusinessException {
    public NotFoundMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
