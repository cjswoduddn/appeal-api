package com.appeal.api.advice;

import com.appeal.api.advice.dto.ErrorResponse;
import com.appeal.api.common.exception.DuplicateEmailException;
import com.appeal.api.common.exception.FailImageUploadException;
import com.appeal.api.common.exception.NoSupportDtoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> duplicateEmailExceptionHandler(DuplicateEmailException exception) {
        ErrorResponse body = new ErrorResponse(exception.getMessage());
        log.info("DuplicatException : {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(body);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        ErrorResponse body = new ErrorResponse(exception.getMessage());
        log.info("Method Arg 이상해 : {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(FailImageUploadException.class)
    public ResponseEntity<ErrorResponse> failImageUploadExceptionHandler(FailImageUploadException exception){
        ErrorResponse body = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(body);
    }

    @ExceptionHandler(NoSupportDtoException.class)
    public ResponseEntity<ErrorResponse> noSupportDtoException(NoSupportDtoException exception){
        ErrorResponse body = new ErrorResponse(exception.getMessage());
        log.info("더이상 지원하지 않는 dto입니다");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(body)
                ;
    }
}
