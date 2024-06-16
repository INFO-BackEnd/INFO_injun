package org.example.info_injun.domain.todo.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.info_injun.domain.todo.domain.Todo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
    private int id;
    private int userId;
    private String content;
    private boolean done;

    public static TodoResponseDTO todoResponseDTO(Todo todo) {
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .userId(todo.getUser().getId())
                .content(todo.getContent())
                .done(todo.isDone())
                .build();
    }
}
