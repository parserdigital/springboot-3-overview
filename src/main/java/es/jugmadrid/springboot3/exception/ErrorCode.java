package es.jugmadrid.springboot3.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {

    INVALID(BAD_REQUEST),
    RESOURCE_NOT_FOUND(NOT_FOUND),
    GENERAL_ERROR(INTERNAL_SERVER_ERROR);

    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
