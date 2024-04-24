package com.youneskarir.demo.advice.custom;

public class ElementExistException extends RuntimeException {
    public ElementExistException(String message) {
        super(message);
    }
    public ElementExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
