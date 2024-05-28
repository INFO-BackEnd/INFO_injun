package org.example.info_injun.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.info_injun.dto.request.TodoRequestDTO;
import org.example.info_injun.dto.request.TodoUpdateRequestDTO;
import org.example.info_injun.dto.response.TodoResponseDTO;
import org.example.info_injun.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<TodoResponseDTO> getAllTodos(){
        return todoService.getAllTodos();
    }

    @GetMapping("user/{id}")
    public List<TodoResponseDTO> getTodosByUserId(@PathVariable("id") int id){
        return todoService.getTodosByUserId(id);
    }

    @GetMapping("{id}")
    public TodoResponseDTO getTodosById(@PathVariable("id") int id){
        return todoService.getTodosById(id);
    }

    @PostMapping
    public void creatTodo(@RequestBody TodoRequestDTO todoRequestDTO){
        todoService.creatTodo(todoRequestDTO);
    }

    @PutMapping("{id}")
    public void updateTodo(@PathVariable("id") int id, @RequestBody TodoUpdateRequestDTO todoUpdateRequestDTO){
        todoService.updateTodo(id, todoUpdateRequestDTO);
    }
}
