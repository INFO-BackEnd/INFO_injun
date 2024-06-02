package org.example.info_injun.domain.todo.domain.repository;

import org.example.info_injun.domain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserId(int UserId);
}
