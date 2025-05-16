package com.example.upperWebProgramming.controller;

import com.example.upperWebProgramming.entity.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ExampleController {

    @PostMapping("/todo/create")
    public ResponseEntity<String> createTodo(@RequestBody Todo todo) {
        // 생성 로직
        return ResponseEntity.ok("할 일이 성공적으로 생성되었습니다!");
    }

}
