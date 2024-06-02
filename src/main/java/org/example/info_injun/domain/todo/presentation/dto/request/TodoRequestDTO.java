package org.example.info_injun.domain.todo.presentation.dto.request;

import lombok.Getter;

@Getter
public class TodoRequestDTO {
    private int userId;
    private String content;
}