package sallange.server.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnAuthorizationException extends RuntimeException {

    private final String exceptionMessage;
}
