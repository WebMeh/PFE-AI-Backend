package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.ExamResult;
import com.pfe.ai.ai.model.dto.ExamResultDTO;
import com.pfe.ai.ai.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ExamResultController {
    private final ExamResultService examResultService;

    @PostMapping
    public ResponseEntity<?> submitExam(@RequestBody ExamResultDTO examResultDTO){
        ExamResult examResult = examResultService.submitExam(examResultDTO);
        if (examResult == null)
            return ResponseEntity.ok("Failed to submit response of exam"+examResultDTO.examId());
        return ResponseEntity.ok(examResult);
    }

    // Get exams of a specific teacher
    @GetMapping("/submitted-exams-of-teacher/{teacherId}")
    public ResponseEntity<?> getSubmittedExamsOfTeacher(@PathVariable Long teacherId) {
        List<ExamResult> examResultList = examResultService.getSubmittedExamsByTeacher(teacherId);
        if (examResultList == null)
            return ResponseEntity.ok("Teacher Not found with ID : "+teacherId);
        return ResponseEntity.ok(examResultList);
    }

    // Get submitted exams by exam id
    @GetMapping("/submitted-exams-of-exam/{examId}")
    public ResponseEntity<?> getSubmittedExamsByExamId(@PathVariable Long examId) {
        return ResponseEntity.ok(examResultService.getSubmittedExamsByExamId(examId));
    }

    @GetMapping("/submitted-exams")
    public ResponseEntity<?> getSubmittedExams () {
        return ResponseEntity.ok(examResultService.getSubmittedExams());
    }

    @GetMapping("/submitted-exams/{studentId}")
    public ResponseEntity<?> getSubmittedExamsOfStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(examResultService.getSubmittedExamsOfStudent(studentId));
    }
    @GetMapping("/available-exams")
    public ResponseEntity<?> getAvailableExams() {
        return ResponseEntity.ok(examResultService.getAvailableExams());
    }
}
