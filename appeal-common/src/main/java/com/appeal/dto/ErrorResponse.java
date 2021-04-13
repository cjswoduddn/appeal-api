package com.appeal.dto;

import com.appeal.code.ErrorCode;
import lombok.Getter;


@Getter
public class ErrorResponse {
    private final String message;
    private final int status;
    private final String code;

//    private List<FieldError> errors;


    private ErrorResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getMessage(), errorCode.getStatus(), errorCode.getCode());
    }

    /*
    public static class FieldError{
        private String field;
        private String value;
        private String code;
    }
     */
}
