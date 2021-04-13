package com.appeal.dto;

import com.appeal.code.SuccessCode;
import lombok.Getter;

import java.util.List;

@Getter
public class SuccessResponse<D> {
    private String message;
    private int status;
    private String code;
    private D data;


    private SuccessResponse(String message, int status, String code, D data) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.data = data;
    }

    private SuccessResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static<D> SuccessResponse of(SuccessCode successCode, D data){
        return new SuccessResponse(successCode.getMessage(), successCode.getStatus(), successCode.getCode(), data);
    }

    public static SuccessResponse of(SuccessCode successCode){
        return new SuccessResponse(successCode.getMessage(), successCode.getStatus(), successCode.getCode());
    }
}
