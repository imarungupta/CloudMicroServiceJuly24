package com.sis.customer.service.api.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiExceptionMessage {

    private String message;
    private boolean success;
    private HttpStatus httpStatus;

}
