package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class UnexpectedMethodArgumentNullPointerException extends BusinessException {
    public UnexpectedMethodArgumentNullPointerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
