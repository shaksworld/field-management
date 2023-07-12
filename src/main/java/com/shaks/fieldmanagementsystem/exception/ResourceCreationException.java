package com.shaks.fieldmanagementsystem.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Setter
@Getter
public class ResourceCreationException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public ResourceCreationException(String message){
        super(message);
        this.message  = message;
    }
    public ResourceCreationException(String message, HttpStatus status){
        super(message);
        this.message  = message;
        this.status =status;
    }
}
