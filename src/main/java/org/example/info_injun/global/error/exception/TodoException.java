package org.example.info_injun.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoException extends RuntimeException {
    private final ErrorCode errorCode;
}
