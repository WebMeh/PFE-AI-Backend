package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.model.dto.EnrollmentDto;
import com.pfe.ai.ai.service.AccountService;
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
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    private final CourseService courseService;
    private final AccountService accountService;

    @PostMapping("/enroll-course")
    public ResponseEntity<?> enrollCourse(
            @RequestBody EnrollmentDto enrollmentDto) {
        Enrollment enrollment = courseService.enrollStudent(
                enrollmentDto.userId(),
                enrollmentDto.courseId()
        );
        if (enrollment == null) return ResponseEntity.ok("Echec d'inscription au cours, etuiant n'existe pas ou le cours n'xiste pas");
        return ResponseEntity.ok(enrollment);
    }

    // Get student enrolled courses
    @GetMapping("/my-courses/{studentId}")
    public ResponseEntity<?> getStudentCourses(@PathVariable Long studentId){
        return ResponseEntity.ok(courseService.getStudentCourses(studentId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.ok(accountService.listUsers());
    }
}
