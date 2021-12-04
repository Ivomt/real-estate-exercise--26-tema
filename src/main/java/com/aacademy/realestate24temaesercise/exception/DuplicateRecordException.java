package com.aacademy.realestate24temaesercise.exception;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends RuntimeException{

    public DuplicateRecordException(String message) {
        super(message);
    }
}
