package com.example.upperWebProgramming.dto;

import com.example.upperWebProgramming.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {

    private Long id;
    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private boolean completed;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public TodoDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.dueDate = todo.getDueDate();
        this.completed = todo.isCompleted();
        this.createdAt = todo.getCreatedAt();
    }

    public Todo toEntity() {
        return Todo.builder()
                .id(id)
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .completed(completed)
                .build();
    }
}