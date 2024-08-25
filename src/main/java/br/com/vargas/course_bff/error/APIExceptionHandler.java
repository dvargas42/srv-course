package br.com.vargas.course_bff.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.vargas.course_bff.exception.CustomException;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleRuntimeException(
            RuntimeException ex, WebRequest request) {

        ErrorEntity error = new ErrorEntity(
            HttpStatus.BAD_REQUEST,
            "Request error",
            ex.getMessage(),
            request);

        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(
            RuntimeException ex, WebRequest request) {

        ErrorEntity error = new ErrorEntity(
            HttpStatus.BAD_REQUEST,
            "Request error",
            ex.getMessage(),
            request);

        return buildResponseEntity(error);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorEntity error) {
        return new ResponseEntity<Object>(error, error.getStatus());
    }
}
