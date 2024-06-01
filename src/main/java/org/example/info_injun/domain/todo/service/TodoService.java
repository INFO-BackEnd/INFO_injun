package org.example.info_injun.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.info_injun.domain.todo.entity.Todo;
import org.example.info_injun.domain.user.entity.User;
import org.example.info_injun.exception.NotFoundException;
import org.example.info_injun.infrastructure.repository.JpaTodoRepository;
import org.example.info_injun.infrastructure.repository.JpaUserRepository;
import org.example.info_injun.interfaces.todo.dto.request.TodoRequestDTO;
import org.example.info_injun.interfaces.todo.dto.request.TodoUpdateRequestDTO;
import org.example.info_injun.interfaces.todo.dto.response.TodoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final JpaTodoRepository jpaTodoRepository;
    private final JpaUserRepository jpaUserRepository;

    public List<TodoResponseDTO> getAllTodos(){
        List<Todo> todos = jpaTodoRepository.findAll();

        return todos.stream().map(todo -> TodoResponseDTO.builder()
                .id(todo.getId())
                .userId(todo.getId())
                .content(todo.getContent())
                .done(todo.isDone())
                .build()).toList();
    }

    public List<TodoResponseDTO> getTodosByUserId(int id){
        List<Todo> todos = jpaTodoRepository.findByUserId(id);

        return todos.stream().map(todo -> TodoResponseDTO.builder()
                .id(todo.getId())
                .userId(todo.getId())
                .content(todo.getContent())
                .done(todo.isDone())
                .build()).toList();
    }

    public TodoResponseDTO getTodosById(int id){
        Optional<Todo> todoOptional = jpaTodoRepository.findById(id);
        if (todoOptional.isEmpty()){
            throw new NotFoundException("Todo not found with id: " + id);
        }

        return TodoResponseDTO.builder()
                .id(todoOptional.get().getId())
                .userId(todoOptional.get().getUser().getId())
                .content(todoOptional.get().getContent())
                .done(todoOptional.get().isDone()).build();
    }

    public void creatTodo(TodoRequestDTO todoRequestDTO){
        Optional<User> userOptional = jpaUserRepository.findById(todoRequestDTO.getUserId());
        if (userOptional.isEmpty()){
            throw new NotFoundException("user not found with id: " + todoRequestDTO.getUserId());
        }

        Todo todo = Todo.builder()
                .user(userOptional.get())
                .content(todoRequestDTO.getContent()).build();

        jpaTodoRepository.save(todo);
    }

    public void updateTodo(int id, TodoUpdateRequestDTO todoUpdateRequestDTO){
        Optional<Todo> todoOptional = jpaTodoRepository.findById(id);
        if (todoOptional.isEmpty()){
            throw new NotFoundException("todo not found with id: " + id);
        }

        Todo todo = todoOptional.get();
        if (todoUpdateRequestDTO.isDone()) {
            todo.completeTodo();
        } else {
            todo.cancelTodo();
        }

        jpaTodoRepository.save(todo);
    }
}
