package com.monolithic.cinema.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST("E400", "Uncategorized Bad request", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("E401", "Unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("E403", "Forbidden", HttpStatus.FORBIDDEN),
    RESOURCE_NOT_FOUND("E404", "%s not found", HttpStatus.NOT_FOUND),
    RESOURCE_ALREADY_EXISTS("E409", "%s already exists", HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR("E500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE("E503", "Service unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT("E504", "Gateway timeout", HttpStatus.GATEWAY_TIMEOUT),
    INVALID_LENGTH_INPUT("E400_INVALID_LENGTH_INPUT", "{field} must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT_INPUT("E400_INVALID_FORMAT_INPUT", "{field} format is not valid", HttpStatus.BAD_REQUEST),
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    public String getMessage(String resourceName) {
        return String.format(message, resourceName);
    }

}
