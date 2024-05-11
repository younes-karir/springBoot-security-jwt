package com.youneskarir.demo.advice.custom;

public class NotValidEnumValueException extends RuntimeException{
    public NotValidEnumValueException(String message) {
        super(message);
    }

    public NotValidEnumValueException() {
    }
}
