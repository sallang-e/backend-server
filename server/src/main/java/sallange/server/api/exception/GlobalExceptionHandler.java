package sallange.server.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sallange.server.exception.RentException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RentException.class)
    public ResponseEntity<RentExceptionResponse> handleRentException(final RentException e) {
        return ResponseEntity
                .badRequest()
                .body(new RentExceptionResponse(e.getErrorCode(), e.getMessage()));
    }
}
