package com.monolithic.cinema.exception;

import lombok.Getter;
import lombok.Setter;

import com.monolithic.cinema.enums.ErrorCode;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;
    private final String resourceName;

    public CustomException(ErrorCode errorCode, String resourceName) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.resourceName = resourceName;
    }

    public String getMessage() {
        return errorCode.getMessage(resourceName);
    }
}
