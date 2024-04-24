package com.youneskarir.demo.advice.custom;

public class EmptyTokenFieldException extends RuntimeException {
    public EmptyTokenFieldException(String message) {
        super(message);
    }

    public EmptyTokenFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
