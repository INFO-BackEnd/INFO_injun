package org.example.info_injun.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoException extends RuntimeException {
    private final ErrorCode errorCode;
}
