package com.example.upperWebProgramming.service;

import com.example.upperWebProgramming.dto.TodoDTO;
import com.example.upperWebProgramming.model.Todo;
import com.example.upperWebProgramming.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // 모든 할 일 조회
    public List<TodoDTO> findAllTodos() {
        log.info("모든 할 일 목록을 조회합니다.");
        return todoRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }

    // ID로 특정 할 일 조회
    public TodoDTO findTodoById(Long id) {
        log.info("ID {}에 해당하는 할 일을 조회합니다.", id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 존재하지 않습니다. id=" + id));
        return new TodoDTO(todo);
    }

    // 할 일 생성
    public TodoDTO saveTodo(TodoDTO todoDTO) {
        log.info("새로운 할 일을 저장합니다: {}", todoDTO.getTitle());
        Todo todo = todoDTO.toEntity();
        Todo savedTodo = todoRepository.save(todo);
        return new TodoDTO(savedTodo);
    }
}