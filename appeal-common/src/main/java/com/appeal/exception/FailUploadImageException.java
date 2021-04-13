package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class FailUploadImageException extends BusinessException{
    public FailUploadImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
