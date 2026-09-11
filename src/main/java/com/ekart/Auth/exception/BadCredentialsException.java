package com.ekart.Auth.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String invalidCredentials) {
        super("Invalid Credentials");
    }
}
