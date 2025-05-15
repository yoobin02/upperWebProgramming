package com.example.upperWebProgramming.service;

import com.example.upperWebProgramming.model.Todo;
import com.example.upperWebProgramming.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j                  // 로깅을 위한 어노테이션
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // 모든 할 일 조회
    public List<Todo> findAllTodos() {
        log.info("모든 할 일 목록을 조회합니다.");
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }

    // ID로 특정 할 일 조회
    public Todo findTodoById(Long id) {
        log.info("ID {}에 해당하는 할 일을 조회합니다.", id);
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 존재하지 않습니다. id=" + id));
    }

    // 할 일 생성
    public Todo saveTodo(Todo todo) {
        log.info("새로운 할 일을 저장합니다: {}", todo.getTitle());
        return todoRepository.save(todo);
    }
}