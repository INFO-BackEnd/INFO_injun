package org.example.info_injun.infrastructure.repository;

import org.example.info_injun.domain.todo.entity.Todo;
import org.example.info_injun.domain.todo.repository.TodoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTodoRepository extends JpaRepository<Todo, Integer>, TodoRepository {
}
