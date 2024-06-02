package org.example.info_injun.domain.user.exception;

import org.example.info_injun.global.error.exception.ErrorCode;
import org.example.info_injun.global.error.exception.TodoException;

public class UserNotFoundException extends TodoException {
    public static final TodoException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
