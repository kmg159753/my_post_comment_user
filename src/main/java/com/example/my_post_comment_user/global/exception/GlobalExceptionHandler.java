package com.example.assignment_week1.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<?> customExceptionHandler(CustomException ex){
        System.out.println("글로벌 예외처리");
        return new ResponseEntity<>(ex.getErrorCode().getMessage(), ex.getErrorCode().getStatus());
    }
}
