package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class NotSupportDtoException extends BusinessException {
    public NotSupportDtoException(ErrorCode errorCode) {
        super(errorCode);
    }
}
