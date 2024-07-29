package com.sis.hotel.service.api.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiExceptionMessage {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;
}
