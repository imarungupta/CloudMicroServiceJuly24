package com.sis.rating.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> handleRatingNotFoundException(RatingNotFoundException exp){

        // Get the message when exception occurs
        String message = exp.getMessage();

        // Set the exception in class and return as response to user
        ApiExceptionMessage exceptionMsg = ApiExceptionMessage.builder()
                                                              .message(message)
                                                              .success(true)
                                                              .httpStatus(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(exceptionMsg, HttpStatus.NOT_FOUND);

    }
}
