package com.example.upperWebProgramming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data                   // Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor      // 기본 생성자
@AllArgsConstructor     // 모든 필드 포함 생성자
@Builder                // 빌더 패턴 지원
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;  // LocalDateTime에서 LocalDate로 변경


    private boolean completed;

    // priority 필드 삭제
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @Builder
    public void onCreate() {
        createdAt = LocalDate.now();
    }

//    public void updateTitle(Strig titleDto){
//       this.tite = titleDto.title
//    }
}