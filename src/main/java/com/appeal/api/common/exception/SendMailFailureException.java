package com.appeal.api.common.exception;

public class SendMailFailureException extends RuntimeException {
    public SendMailFailureException(String message) {
        super(message);
    }
}
