package com.example.upperWebProgramming.controller;

import com.example.upperWebProgramming.model.Todo;
import com.example.upperWebProgramming.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TodoController {

    private final TodoService todoService;

    // 메인 페이지 (리다이렉트)
    @GetMapping("/")
    public String home() {
        return "redirect:/todos";
    }

    // 할 일 목록 조회
    @GetMapping("/todos")
    public String getTodos(Model model) {
        log.info("할 일 목록 페이지를 조회합니다.");
        model.addAttribute("todos", todoService.findAllTodos());
        return "todo/list";
    }

    // 할 일 생성 폼
    @GetMapping("/todos/new")
    public String createTodoForm() {
        log.info("할 일 생성 폼을 표시합니다.");
        return "todo/create";
    }

    // 할 일 생성 처리
    @PostMapping("/todos")
    public String createTodo(Todo todo) {
        log.info("새로운 할 일을 생성합니다: {}", todo);
        Todo todoInfo = todoService.saveTodo(todo);
        log.info("저장된 정보: {}", todoInfo);
        return "redirect:/todos";
    }

    // 할 일 상세 조회
    @GetMapping("/todos/{id}")
    public String getTodoDetails(@PathVariable Long id, Model model) {
        log.info("할 일 상세 정보를 조회합니다. id: {}", id);
        Todo todo = todoService.findTodoById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedCreatedAt = todo.getCreatedAt().format(formatter);

        model.addAttribute("todo", todo);
        model.addAttribute("formattedCreatedAt", formattedCreatedAt);
        return "todo/detail";
    }
}
