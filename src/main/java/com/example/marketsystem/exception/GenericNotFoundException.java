package com.example.marketsystem.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class GenericNotFoundException extends RuntimeException {
    private String message;
    private Integer statusCode;
}
