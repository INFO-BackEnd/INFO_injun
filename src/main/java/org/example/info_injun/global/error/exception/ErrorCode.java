package org.example.info_injun.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "Internal server error"),
    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    TODO_NOT_FOUND(404, "TODO-404-1", "Todo Not Found");

    private final int status;
    private final String code;
    private final String message;
}
