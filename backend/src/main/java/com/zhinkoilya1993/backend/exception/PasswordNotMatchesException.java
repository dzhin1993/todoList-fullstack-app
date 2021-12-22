package com.zhinkoilya1993.backend.exception;

public class PasswordNotMatchesException extends RuntimeException {
    public PasswordNotMatchesException(String message) {
        super(message);
    }
}
