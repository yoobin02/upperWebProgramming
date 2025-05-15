package com.example.upperWebProgramming.controller;

import com.example.upperWebProgramming.dto.TodoDTO;
import com.example.upperWebProgramming.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TodoController {

    private final TodoService todoService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm", Locale.KOREAN);

    @GetMapping("/")
    public String home() {
        return "redirect:/todos";
    }

    // 할 일 목록 조회
    @GetMapping("/todos")
    public String getTodos(Model model) {
        //log.info("할 일 목록 페이지를 조회합니다.");
        List<TodoDTO> todos = todoService.findAllTodos();
        for (TodoDTO todo : todos) {
            todo.formatDates();
        }

        model.addAttribute("todos", todos);
        return "todo/list";
    }

    // 할 일 생성 폼
    @GetMapping("/todos/new")
    public String createTodoForm(Model model) {
        log.info("할 일 생성 폼을 표시합니다.");

        LocalDateTime now = LocalDateTime.now();
        String localTime = now.format(formatter);

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setDueDate(now);
        todoDTO.setCreatedAt(now);

        model.addAttribute("todoDTO", todoDTO);
        model.addAttribute("time", localTime);
        model.addAttribute("currentDateTime", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

        return "todo/create";
    }

    // 할 일 생성 처리
    @PostMapping("/todos")
    public String createTodo(@ModelAttribute TodoDTO todoDTO) {
        //log.info("새로운 할 일을 생성합니다: {}", todoDTO);
        TodoDTO savedTodo = todoService.saveTodo(todoDTO);

        savedTodo.formatDates();
        log.info("저장된 정보: {}", savedTodo);

        return "redirect:/todos";
    }

    // 특정 할 일 상세 조회
    @GetMapping("/todos/{id}")
    public String getTodoDetails(@PathVariable Long id, Model model) {
        log.info("할 일 상세 정보를 조회합니다. id: {}", id);
        TodoDTO todoDTO = todoService.findTodoById(id);
        todoDTO.formatDates();

        model.addAttribute("todo", todoDTO);
        return "todo/detail";
    }
}