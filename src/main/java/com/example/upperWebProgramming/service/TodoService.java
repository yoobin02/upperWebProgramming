package com.example.upperWebProgramming.service;

import com.example.upperWebProgramming.dto.TodoDTO;
import com.example.upperWebProgramming.entity.Todo;
import com.example.upperWebProgramming.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 모든 할 일 조회
    public List<TodoDTO> findAllTodos() {
        return todoRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }

    // 할 일 저장
    public TodoDTO saveTodo(TodoDTO todoDTO) {
        Todo todo = todoDTO.toEntity();
        Todo savedTodo = todoRepository.save(todo);
        return new TodoDTO(savedTodo);
    }

    // ID로 특정 할 일 조회
    public TodoDTO findTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 할 일을 찾을 수 없습니다: " + id));
        return new TodoDTO(todo);
    }

    // 주어진 ID를 가진 할 일 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
    //전체 투두와 현재 투두 계산
    public int todoPercent(List<TodoDTO> todos) {
        if (todos.isEmpty()) return 0;

        long completeTodo = todos.stream()
                .filter(TodoDTO::isCompleted)
                .count();

        return (int) ((completeTodo / (float) todos.size()) * 100);
    }

}