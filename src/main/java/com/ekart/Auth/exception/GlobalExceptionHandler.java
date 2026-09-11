package com.ekart.Auth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(NoSuchAlogrithmExceptionNT.class)
    public ResponseEntity hadlerNoSuchAlgorithmException(NoSuchAlogrithmExceptionNT ex)
    {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" No RSA alog found");

    }*/

    public ResponseEntity hadlerBADCredentialsException(BadCredentialsException ex)
    {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid Credentials");


    }


}
