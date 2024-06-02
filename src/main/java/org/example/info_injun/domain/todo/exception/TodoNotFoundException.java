package org.example.info_injun.domain.todo.exception;

import org.example.info_injun.global.error.exception.ErrorCode;
import org.example.info_injun.global.error.exception.TodoException;

public class TodoNotFoundException extends TodoException {
    public static final TodoException EXCEPTION = new TodoNotFoundException();

    private TodoNotFoundException() {
        super(ErrorCode.TODO_NOT_FOUND);
    }
}
