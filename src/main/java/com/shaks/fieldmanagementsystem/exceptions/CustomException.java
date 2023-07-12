package com.shaks.fieldmanagementsystem.exceptions;


import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    private String message;
    private HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException() {
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status.toString();
    }
}
