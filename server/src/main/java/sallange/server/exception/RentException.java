package sallange.server.exception;

import lombok.Getter;

@Getter
public class RentException extends RuntimeException {

    private final Integer errorCode;

    public RentException(final Integer errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public RentException(final Integer errorCode, final String message) {
        this(errorCode, message, null);
    }
}
