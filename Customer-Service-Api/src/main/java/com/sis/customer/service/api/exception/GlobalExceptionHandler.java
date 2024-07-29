package com.sis.customer.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> handleResourceNotFoundException(ResourceNotFoundException exp){

        String message = exp.getMessage();
        ApiExceptionMessage exceptionMessage= ApiExceptionMessage.builder()
                                                                 .message(message)
                                                                 .success(true)
                                                                 .httpStatus(HttpStatus.NOT_FOUND)
                                                                 .build();

        return new ResponseEntity<>(exceptionMessage,HttpStatus.NOT_FOUND);
    }
    // We can write the above method using Map without creating ApiExceptionMessage Model class
    // but above method is recommended
    /*@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFoundException1(ResourceNotFoundException exp) {

        String message = exp.getMessage();

        Map map = new HashMap();
        map.put("message", message);
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }*/
}
