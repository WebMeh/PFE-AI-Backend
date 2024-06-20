package com.pfe.ai.ai.service.impl;

import com.pfe.ai.ai.model.Cour;
import com.pfe.ai.ai.model.Exam;
import com.pfe.ai.ai.model.ExamResult;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.model.dto.ExamResultDTO;
import com.pfe.ai.ai.repository.CourseRepository;
import com.pfe.ai.ai.repository.ExamRepository;
import com.pfe.ai.ai.repository.ExamResultRepository;
import com.pfe.ai.ai.repository.UserRepository;
import com.pfe.ai.ai.service.CourseService;
import com.pfe.ai.ai.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    @Override
    public List<ExamResult> getSubmittedExamsOfStudent(Long studentId) {
        return examResultRepository.findAll()
                .stream()
                .filter(exam -> exam.getStudent() != null && exam.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResult> getSubmittedExamsByTeacher(Long teacherId) {
        // Fetch the teacher
        User teacher = userRepository.findById(teacherId).orElse(null);
        if(teacher == null )
            return null;

        // Get all submitted exams
        List<ExamResult> allSubmittedExams = examResultRepository.findAll();

        // List of exams of teacher
        List<ExamResult> teacherExams = new ArrayList<>();

        for (ExamResult examResult: allSubmittedExams) {
            if (examResult.getExam().getCourse().getTeacher().getId().equals(teacherId)){
                teacherExams.add(examResult);
            }
        }

        return teacherExams;
    }

    @Override
    public List<ExamResult> getSubmittedExamsByExamId(Long examId) {
        // Get all submitted exams
        List<ExamResult> submittedExams = examResultRepository.findAll();
        List<ExamResult> examResultList = new ArrayList<>();

        for (ExamResult examResult: submittedExams) {
            if (examResult.getExam().getId().equals(examId)) {
                examResultList.add(examResult);
            }
        }
        return examResultList;
    }

    @Override
    public ExamResult submitExam(ExamResultDTO examResultDTO) {
        // Get the exam from db
        Exam exam  = examRepository.findById(examResultDTO.examId())
                .orElse(null);
        // Get student
        User student = userRepository.findById(examResultDTO.studentId())
                .orElse(null);
        if(exam == null || student == null){
            return null;
        }

        ExamResult examResult = new ExamResult();
        examResult.setExam(exam);
        examResult.setStudent(student);
        examResult.setResult(examResultDTO.result());

        return examResultRepository.save(examResult);
    }

    @Override
    public List<ExamResult> getAllExamResults() {
        return examResultRepository.findAll();
    }

    @Override
    public List<Exam> getSubmittedExams() {
        List<ExamResult> sumittedExams = examResultRepository.findAll();
        List<Exam> examList = new ArrayList<>();
        for (ExamResult examResult :sumittedExams) {
            examList.add(examResult.getExam());
        }
        return examList;
    }

    @Override
    public List<Exam> getAvailableExams() {
        List<Exam> allExams = examRepository.findAll();
        List<Exam> submittedExams = new ArrayList<>();
        List<ExamResult> examResultList = examResultRepository.findAll();

        for (ExamResult examResult : examResultList) {
            submittedExams.add(examResult.getExam());
        }

        List<Exam> unSubmittedExams = new ArrayList<>(allExams);
        unSubmittedExams.removeAll(submittedExams);
        return unSubmittedExams;
    }

    @Override
    public List<ExamResult> getResultsOfExam(Long examId) {
        Exam exam = examRepository.findById(examId).orElse(null);
        if(exam == null)
            return null;
        return examResultRepository.findByExam(exam);
    }
}
