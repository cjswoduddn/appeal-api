package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class DuplicateEmailException extends BusinessException{

    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
