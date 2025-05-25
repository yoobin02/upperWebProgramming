package com.example.upperWebProgramming.service;

import com.example.upperWebProgramming.dto.TodoDTO;
import com.example.upperWebProgramming.entity.Todo;
import com.example.upperWebProgramming.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 모든 할 일 조회 (진행 중 -> 완료됨 -> 만료됨 순서 정렬)
    public List<TodoDTO> findAllTodos() {
        List<Todo> allTodos = todoRepository.findAllByOrderByCreatedAtDesc();
        List<TodoDTO> allTodoDTOs = allTodos.stream()
                .map(TodoDTO::new)
                .toList();

        List<TodoDTO> inProgressTodos = new ArrayList<>();
        List<TodoDTO> completedTodos = new ArrayList<>();
        List<TodoDTO> expiredTodos = new ArrayList<>();

        for (TodoDTO todo : allTodoDTOs) {
            if (todo.isExpired()) {
                expiredTodos.add(todo);
            } else if (todo.isCompleted()) {
                completedTodos.add(todo);
            } else {
                inProgressTodos.add(todo);
            }
        }

        List<TodoDTO> sortedTodos = new ArrayList<>();
        sortedTodos.addAll(inProgressTodos);
        sortedTodos.addAll(completedTodos);
        sortedTodos.addAll(expiredTodos);

        return sortedTodos;
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

    // 주거 ID를 가진 할 일 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    // 전체 투두와 현재 투두 계산 (만료된 할 일도 전체에 포함하는데 완료된 것에 포함되지 않게 설정)
    public int todoPercent(List<TodoDTO> todos) {
        if (todos.isEmpty())
            return 0;

        int totalTodos = todos.size();
        long completeTodo = todos.stream()
                .filter(todo -> todo.isCompleted() && !todo.isExpired())
                .count();

        return (int) ((completeTodo / (float) totalTodos) * 100);
    }

    // 여러 개의 할 일 삭제
    public void deleteTodosByIds(List<Long> ids) {
        todoRepository.deleteAllById(ids);
    }

}