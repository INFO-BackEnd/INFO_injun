package org.example.info_injun.interfaces.todo.dto.request;

import lombok.Getter;

@Getter
public class TodoRequestDTO {
    private int userId;
    private String content;
}