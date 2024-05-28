package org.example.info_injun.service;

import lombok.RequiredArgsConstructor;
import org.example.info_injun.dto.request.TodoRequestDTO;
import org.example.info_injun.dto.request.TodoUpdateRequestDTO;
import org.example.info_injun.dto.response.TodoResponseDTO;
import org.example.info_injun.entity.Todo;
import org.example.info_injun.entity.User;
import org.example.info_injun.repository.TodoRepository;
import org.example.info_injun.repository.UserRepository;
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
        if (todoOptional.isPresent()){
            return TodoResponseDTO.builder()
                    .id(todoOptional.get().getId())
                    .userId(todoOptional.get().getUser().getId())
                    .content(todoOptional.get().getContent())
                    .done(todoOptional.get().isDone()).build();
        } else {
            throw new IllegalArgumentException("Todo not found with id: " + id);
        }
    }

    public void creatTodo(TodoRequestDTO todoRequestDTO){
        Optional<User> userOptional = userRepository.findById(todoRequestDTO.getUserId());
        if (userOptional.isPresent()){
            Todo todo = Todo.builder()
                    .user(userOptional.get())
                    .content(todoRequestDTO.getContent()).build();
            todoRepository.save(todo);
        } else {
            throw new IllegalArgumentException("user not found with id: " + todoRequestDTO.getUserId());
        }
    }

    public void updateTodo(int id, TodoUpdateRequestDTO todoUpdateRequestDTO){
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()){
            Todo todo = todoOptional.get();
            todo.setDone(todoUpdateRequestDTO.isDone());
            todoRepository.save(todo);
        } else {
            throw new IllegalArgumentException("todo not found with id: " + id);
        }
    }
}
