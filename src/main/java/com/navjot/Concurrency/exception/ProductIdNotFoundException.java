package com.navjot.Concurrency.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class ProductIdNotFoundException extends RuntimeException{
    public ProductIdNotFoundException(String message) {
        super(message);
    }
}
