package com.shaks.fieldmanagementsystem.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private String message;

    private HttpStatus status;
    public CustomException(String message){
        super(message);
        this.message = message;
    }
    public CustomException(String message, HttpStatus status){
        super(message);
        this.status = status;
        this.message = message;
    }
}
