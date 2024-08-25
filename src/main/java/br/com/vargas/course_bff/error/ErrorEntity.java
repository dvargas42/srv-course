package br.com.vargas.course_bff.error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ErrorEntity {
    
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-NM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private Object cause;

    private ErrorEntity() {
        timestamp = LocalDateTime.now();
    }

    public ErrorEntity(HttpStatus status, String message, String cause, WebRequest request) {
        this();
        this.status = status;
        this.message = message;
        this.cause = cause;

        log.error("Error {} {} {} {} \n", status.toString(), this.message, cause, request.toString());
    }

    public ErrorEntity(HttpStatus status, String message, List<FieldError> fieldsException, WebRequest request) {
        this();
        this.status = status;
        this.message = message;
        this.cause = fieldsException.stream().map(ValidationError::new).toList();

        log.error("Error {} {} {} {} \n", status.toString(), this.message, this.cause, request.toString());
    }

    private record ValidationError(String field, String message) {
    
        public ValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
