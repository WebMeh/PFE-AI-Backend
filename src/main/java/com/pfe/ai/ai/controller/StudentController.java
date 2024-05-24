package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final CourseService courseService;

    @PostMapping("/enroll-course/{courseId}")
    public ResponseEntity<?> enrollCourse(
            @AuthenticationPrincipal UserDetails student, @PathVariable Long courseId) {
        Enrollment enrollment = courseService.enrollStudent(student.getUsername(), courseId);
        if (enrollment == null) return ResponseEntity.ok("Echec d'inscription au cours, etuiant n'existe pas ou le cours n'xiste pas");
        return ResponseEntity.ok(enrollment);
    }

    // Get student enrolled courses
    @GetMapping("/my-courses")
    public ResponseEntity<?> getStudentCourses(@AuthenticationPrincipal UserDetails student){
        return ResponseEntity.ok(courseService.getStudentCourses(student.getUsername()));
    }
}
