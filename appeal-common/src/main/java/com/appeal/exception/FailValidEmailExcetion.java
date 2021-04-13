package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class FailValidEmailExcetion extends BusinessException {
    public FailValidEmailExcetion(ErrorCode errorCode) {
        super(errorCode);
    }
}
