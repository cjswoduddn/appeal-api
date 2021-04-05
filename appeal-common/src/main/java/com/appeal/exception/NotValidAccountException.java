package com.appeal.exception;

import org.springframework.security.core.AuthenticationException;

public class NotValidAccountException extends AuthenticationException {
    public NotValidAccountException(String msg) {
        super(msg);
    }
}
