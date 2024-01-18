package com.example.marketsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<?> mediaTypeException(HttpMediaTypeNotAcceptableException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
