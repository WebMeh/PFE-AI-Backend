package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.*;
import com.pfe.ai.ai.model.dto.ExamQuestionDTO;
import com.pfe.ai.ai.model.dto.ExamWithQuestions;
import com.pfe.ai.ai.service.CourseService;
import com.pfe.ai.ai.service.ExamResultService;
import com.pfe.ai.ai.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamController {
    private final ExamService examService;
    private final CourseService courseService;
    private final ExamResultService examResultService;

    // create new exam
    @PostMapping("/create/for-course/{courseId}")
    public ResponseEntity<?> createExam(@RequestBody ExamWithQuestions examWithQuestions, @PathVariable Long courseId){
        Exam createdExam = examService.createExam(examWithQuestions.exam(),  courseId, examWithQuestions.questions());

        if(createdExam != null)
            return ResponseEntity.ok(createdExam);
        return ResponseEntity.ok("Failed to create exam : course not found with id: "+courseId);
    }

    // Add question to an exam
    @PostMapping("/{examId}/add-question")
    public ResponseEntity<?> addQuestionToExam(
            @RequestBody ExamQuestionDTO question,
            @PathVariable Long examId){
         Exam ex= examService.submitExam(question, examId);
        return ResponseEntity.ok(
                ex !=null ?
                        ex:
                        "Failed to add question"
        );
    }

    // Add Options to a question of exam
    @PostMapping("/question/{questionId}/add-option")
    public ResponseEntity<?> addOptionToQuestion(
            @RequestBody Option option,
            @PathVariable Long questionId
            ){
        ExamQuestion examQuestion = examService.addResponseToQuestion(questionId, option);
        if (examQuestion != null)
            return ResponseEntity.ok(examQuestion);
        return ResponseEntity.ok("Failed to add the option : <<"+option.getText()+">> !");
    }
    // Get Exam by id
    @GetMapping("/{examId}")
    public ResponseEntity<?> getExamById(@PathVariable Long examId){
        Exam savedExam = examService.getExamById(examId);
        if (savedExam != null)
            return ResponseEntity.ok(savedExam);
        return ResponseEntity.ok("Exam not found with id "+ examId);
    }

    // Get all questions of an exam
    @GetMapping("/{examId}/questions")
    public ResponseEntity<?> getQuestionsOfExam(@PathVariable Long examId){
        return ResponseEntity.ok(examService.getQuestionsOfExam(examId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExams() {
        return  ResponseEntity.ok(
                examService.getAllExams()
        );
    }

    // Display exam for each user
    @GetMapping("/my-exams/{studentId}")
    public ResponseEntity<?> getStudentExams(@PathVariable Long studentId){
        List<Cour> studentCourses = courseService.getStudentCourses(studentId);
        List<Long> courseIds = studentCourses.stream().map(Cour::getId).collect(Collectors.toList());
        List<Exam> allExams = examService.getAllExams();
        List <Exam> studentExams = allExams.stream()
                .filter(exam -> courseIds.contains(exam.getCourse().getId()))
                .collect(Collectors.toList());
        List<ExamResult> examResultList = examResultService.getAllExamResults();
        List<Exam> submittedExam = new ArrayList<>();

        for (ExamResult examResult : examResultList) {
                submittedExam.add(examResult.getExam());
        }
        Set<Long> completedExamIds = new HashSet<>();
        for (Exam exam : submittedExam) {
            completedExamIds.add(exam.getId());
        }

        List<Exam> uncompletedExams = new ArrayList<>();
        for (Exam exam : studentExams) {
            if (!completedExamIds.contains(exam.getId())) {
                uncompletedExams.add(exam);
            }
        }
        return ResponseEntity.ok(uncompletedExams );

    }

    @GetMapping("/of-teacher/{teacherId}")
    public ResponseEntity<?> getTeacherExams(@PathVariable Long teacherId){
        List<Exam> examList = examService.getTeacherExams(teacherId);

        if(examList == null)
            return ResponseEntity.ok("Teacher Not Found With Id : "+teacherId);
        return  ResponseEntity.ok(examList);
    }

}
