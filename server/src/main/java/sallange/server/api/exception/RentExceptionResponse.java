package sallange.server.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RentExceptionResponse {

    private final Integer errorCode;
    private final String errorMessage;
}
