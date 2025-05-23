package com.example.upperWebProgramming.dto;

import com.example.upperWebProgramming.entity.Todo;
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
                .createdAt(createdAt)
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

    // 마감일이 지났는지 확인하는 메소드
    public boolean isExpired() {
        if (dueDate == null)
            return false; // 마감일이 없으면 만료되지 않음

        // 현재 시간이 마감일 이후이고 완료되지 않았다면 만료됨 처리
        return LocalDateTime.now().isAfter(dueDate) && !completed;
    }

    // 상태를 문자열로 반환하는 메소드
    public String getStatusText() {
        if (isExpired()) {
            return "만료됨";
        } else if (completed) {
            return "완료됨";
        } else {
            return "진행 중";
        }
    }

    public String getStatusClass() {
        if (isExpired()) {
            return "bg-danger";
        } else if (completed) {
            return "bg-success";
        } else {
            return "bg-warning";
        }
    }

    @Override
    public String toString() {
        formatDates();

        return "TodoDTO(" +
                "id=" + id +
                ", 제목=" + title +
                ", 설명=" + description +
                ", 생성일: " + formattedCreatedAt +
                ", 마감일: " + formattedDueDate +
                ", 진행도=" + getStatusText() +
                ")";
    }
}