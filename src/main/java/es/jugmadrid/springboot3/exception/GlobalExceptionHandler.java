package es.jugmadrid.springboot3.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    ProblemDetail handleServiceException(ServiceException e) {
        log.error(e.getDescription());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getErrorCode().getHttpStatus(), e.getDescription());
        problemDetail.setTitle(e.getErrorCode().getHttpStatus().name());
        problemDetail.setType(URI.create("https://example.com/exceptions"));
        return problemDetail;
    }
}