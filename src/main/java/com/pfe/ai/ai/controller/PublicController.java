package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.service.CourseService;
import com.pfe.ai.ai.system.Result;
import com.pfe.ai.ai.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:5173")
public class PublicController {
    private final CourseService courseService;

    // Get all courses
    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.ok(
                new Result(
                        true, StatusCode.SUCCESS, "List of all courses",
                        courseService.getAllCourses()
                )
        );
    }
}
