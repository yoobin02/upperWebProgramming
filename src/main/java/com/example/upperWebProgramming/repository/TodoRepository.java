package com.example.upperWebProgramming.repository;

import com.upperwebprogramming.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompletedOrderByDueDateAsc(boolean completed);
    List<Todo> findAllByOrderByPriorityDescDueDateAsc();
}