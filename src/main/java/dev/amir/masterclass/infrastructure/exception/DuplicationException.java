package dev.amir.masterclass.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicationException extends RuntimeException {
    public DuplicationException(String message) {
        super(message);
    }
}
