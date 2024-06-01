package org.example.info_injun.interfaces.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDTO {
    private int id;
    private int userId;
    private String content;
    private boolean done;
}
