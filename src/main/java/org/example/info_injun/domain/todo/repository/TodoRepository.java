package org.example.info_injun.domain.todo.repository;

import org.example.info_injun.domain.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findByUserId(int UserId);
}
