package com.example.upperWebProgramming.service;

import com.upperwebprogramming.model.Todo;
import com.upperwebprogramming.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAllByOrderByPriorityDescDueDateAsc();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = getTodoById(id);

        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setDueDate(todoDetails.getDueDate());
        todo.setPriority(todoDetails.getPriority());
        todo.setCompleted(todoDetails.isCompleted());

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todoRepository.delete(todo);
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompletedOrderByDueDateAsc(true);
    }

    public List<Todo> getActiveTodos() {
        return todoRepository.findByCompletedOrderByDueDateAsc(false);
    }
}