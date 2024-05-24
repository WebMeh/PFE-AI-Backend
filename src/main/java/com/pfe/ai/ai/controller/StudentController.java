package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
