package com.appeal.exception;

import com.appeal.code.ErrorCode;

public class NotFoundPortfolioException extends BusinessException {

    public NotFoundPortfolioException(ErrorCode errorCode) {
        super(errorCode);
    }
}
