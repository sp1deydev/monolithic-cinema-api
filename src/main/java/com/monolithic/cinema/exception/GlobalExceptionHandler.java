package com.monolithic.cinema.exception;

import com.monolithic.cinema.dto.Response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    ResponseEntity<ErrorResponse> handlingAppException(CustomException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(exception.getErrorCode().getCode());
        errorResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(errorResponse);
    }
}
