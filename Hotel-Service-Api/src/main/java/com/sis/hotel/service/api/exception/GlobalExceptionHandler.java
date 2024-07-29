package com.sis.hotel.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> handleHotelNotFoundException(HotelNotFoundException exp){

        String message = exp.getMessage();

        ApiExceptionMessage exceptionMessage = ApiExceptionMessage.builder()
                .message(message)
                .success(true)
                .httpStatus(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<ApiExceptionMessage>(exceptionMessage,HttpStatus.NOT_FOUND);

    }
}
