package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class FailSendMailException extends BusinessException {

    public FailSendMailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
