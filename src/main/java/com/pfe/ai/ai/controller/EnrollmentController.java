package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Enrollment;
import com.pfe.ai.ai.repository.EnrollmentRepository;
import com.pfe.ai.ai.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentRepository enrollmentRepository;

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getEnrolledStudents (@PathVariable Long courseId) {
        List<Enrollment> allEnrollments = enrollmentRepository.findAll();
        List<Enrollment> filteredEnrollments = allEnrollments.stream()
                .filter(enrollment -> enrollment.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredEnrollments);
    }


}
