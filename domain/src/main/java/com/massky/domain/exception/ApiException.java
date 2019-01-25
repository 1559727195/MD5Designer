package com.massky.domain.exception;

public class ApiException extends RuntimeException {
    public int code;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }
}
