package org.example.info_injun.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.info_injun.domain.todo.domain.Todo;
import org.example.info_injun.domain.todo.exception.TodoNotFoundException;
import org.example.info_injun.domain.todo.domain.repository.TodoRepository;
import org.example.info_injun.domain.todo.presentation.dto.request.TodoRequestDTO;
import org.example.info_injun.domain.todo.presentation.dto.request.TodoUpdateRequestDTO;
import org.example.info_injun.domain.todo.presentation.dto.response.TodoResponseDTO;
import org.example.info_injun.domain.user.domain.User;
import org.example.info_injun.domain.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public List<TodoResponseDTO> getAllTodos(){
        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(todo -> TodoResponseDTO.builder()
                .id(todo.getId())
                .userId(todo.getId())
                .content(todo.getContent())
                .done(todo.isDone())
                .build()).toList();
    }

    public List<TodoResponseDTO> getTodosByUserId(int id){
        List<Todo> todos = todoRepository.findByUserId(id);

        return todos.stream().map(todo -> TodoResponseDTO.builder()
                .id(todo.getId())
                .userId(todo.getId())
                .content(todo.getContent())
                .done(todo.isDone())
                .build()).toList();
    }

    public TodoResponseDTO getTodosById(int id){
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()){
            throw TodoNotFoundException.EXCEPTION;
        }

        return TodoResponseDTO.builder()
                .id(todoOptional.get().getId())
                .userId(todoOptional.get().getUser().getId())
                .content(todoOptional.get().getContent())
                .done(todoOptional.get().isDone()).build();
    }

    public void creatTodo(TodoRequestDTO todoRequestDTO){
        Optional<User> userOptional = userRepository.findById(todoRequestDTO.getUserId());
        if (userOptional.isEmpty()){
            throw TodoNotFoundException.EXCEPTION;
        }

        Todo todo = Todo.builder()
                .user(userOptional.get())
                .content(todoRequestDTO.getContent()).build();

        todoRepository.save(todo);
    }

    public void updateTodo(int id, TodoUpdateRequestDTO todoUpdateRequestDTO){
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()){
            throw TodoNotFoundException.EXCEPTION;
        }

        Todo todo = todoOptional.get();
        if (todoUpdateRequestDTO.isDone()) {
            todo.completeTodo();
        } else {
            todo.cancelTodo();
        }

        todoRepository.save(todo);
    }
}
