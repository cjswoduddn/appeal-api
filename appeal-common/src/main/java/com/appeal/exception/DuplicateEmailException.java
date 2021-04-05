package com.appeal.exception;

public class DuplicateEmailException extends BusinessException{

    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
