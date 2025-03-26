package com.monolithic.cinema.exception;

import com.monolithic.cinema.dto.Response.ErrorResponse;
import com.monolithic.cinema.enums.ErrorCode;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = CustomException.class)
    ResponseEntity<ErrorResponse> handlingAppException(CustomException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(exception.getErrorCode().getCode());
        errorResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(errorResponse);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> hanlingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String,Object> attributes = null;
        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation =
                    exception.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            log.info(attributes.toString());
        } catch (IllegalArgumentException e) {

        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errorCode.getCode());
        errorResponse.setMessage(
                Objects.nonNull(attributes)
                        ? mapAttribute(errorCode.getMessage(), attributes)
                        : errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);

    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }

}
