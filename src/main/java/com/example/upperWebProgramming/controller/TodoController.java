package com.example.upperWebProgramming.controller;

import com.upperwebprogramming.model.Todo;
import com.upperwebprogramming.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String getAllTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("newTodo", new Todo());
        return "todos/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todos/create";
    }

    @PostMapping
    public String createTodo(@ModelAttribute Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/{id}")
    public String showTodoDetails(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.getTodoById(id));
        return "todos/detail";
    }
}