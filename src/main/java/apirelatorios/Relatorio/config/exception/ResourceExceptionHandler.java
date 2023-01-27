package apirelatorios.Relatorio.config.exception;

import apirelatorios.Relatorio.application.controller.exception.NotFoundException;
import apirelatorios.Relatorio.application.controller.exception.UnexpectedError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError(Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UnexpectedError.class)
    public ResponseEntity<StandardError> unexpectedError(UnexpectedError e, HttpServletRequest request) {
        StandardError error = new StandardError(Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error Unexpected", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}