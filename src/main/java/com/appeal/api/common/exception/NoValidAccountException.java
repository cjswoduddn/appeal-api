package com.appeal.api.common.exception;

import org.springframework.security.core.AuthenticationException;

public class NoValidAccountException extends AuthenticationException {
    public NoValidAccountException(String msg) {
        super(msg);
    }
}
