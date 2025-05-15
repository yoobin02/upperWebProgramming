package com.example.upperWebProgramming.dto;

import com.example.upperWebProgramming.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {

    private Long id;
    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    private boolean completed;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;
    private String formattedDueDate;
    private String formattedCreatedAt;

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

    // 날짜 포맷팅 메소드
    public void formatDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm", Locale.KOREAN);

        if (this.dueDate != null) {
            this.formattedDueDate = this.dueDate.format(formatter);
        }

        if (this.createdAt != null) {
            this.formattedCreatedAt = this.createdAt.format(formatter);
        }
    }

    @Override
    public String toString() {
        formatDates();

        return "TodoDTO(" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", 생성일: " + formattedCreatedAt +
                ", 마감일: " + formattedDueDate +
                ", 진행도=" + completed +
                ")";
    }
}