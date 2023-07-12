package com.shaks.fieldmanagementsystem.exceptions;

import com.shaks.fieldmanagementsystem.dto.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<APIResponse> handleCustomException(CustomException ex){
      APIResponse apiResponse = APIResponse.builder()
              .status(ex.getStatus())
              .results(ex.getMessage())
              .build();
    return ResponseEntity.ok(apiResponse);
    }
}
