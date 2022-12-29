package es.jugmadrid.springboot3.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final String description;
    private final ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public ServiceException(ErrorCode errorCode, String description, Throwable inCause) {
        super(inCause.getMessage(), inCause);
        this.errorCode = errorCode;
        this.description = description;
    }
}