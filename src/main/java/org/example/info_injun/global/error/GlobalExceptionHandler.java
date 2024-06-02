package org.example.info_injun.global.error;

import org.example.info_injun.global.error.exception.ErrorCode;
import org.example.info_injun.global.error.exception.TodoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorResponse> handleGlobal(TodoException exception) {
        final ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build(),
                HttpStatus.valueOf(errorCode.getStatus()));
    }
}
