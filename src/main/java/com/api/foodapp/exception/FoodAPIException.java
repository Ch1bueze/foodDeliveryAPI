package com.api.foodapp.exception;

import org.springframework.http.HttpStatus;

public class FoodAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public FoodAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public FoodAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
