package com.appeal.exception;

public class NotFoundPortfolioException extends BusinessException {

    public NotFoundPortfolioException(ErrorCode errorCode) {
        super(errorCode);
    }
}
