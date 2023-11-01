package sallange.server.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sallange.server.exception.RentException;
import sallange.server.exception.UnAuthorizationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RentException.class)
    public ResponseEntity<RentExceptionResponse> handleRentException(final RentException e) {
        return ResponseEntity
                .badRequest()
                .body(new RentExceptionResponse(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(UnAuthorizationException.class)
    public ResponseEntity<ExceptionResponse> handleUnAuthorizationException(final UnAuthorizationException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(e.getExceptionMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(new ExceptionResponse(e.getMessage()));
    }
}
