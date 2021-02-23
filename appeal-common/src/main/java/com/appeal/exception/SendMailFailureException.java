package com.appeal.exception;

public class SendMailFailureException extends RuntimeException {
    public SendMailFailureException(String message) {
        super(message);
    }
}
