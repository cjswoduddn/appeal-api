package com.appeal.exception;

public class FailUploadImageException extends BusinessException{
    public FailUploadImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
