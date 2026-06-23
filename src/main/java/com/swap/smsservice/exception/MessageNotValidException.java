package com.swap.smsservice.exception;

public class MessageNotValidException extends RuntimeException {
    public MessageNotValidException() {
        super("Not a Valid message!");
    }
}
