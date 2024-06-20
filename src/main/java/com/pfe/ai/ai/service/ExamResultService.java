package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.Exam;
import com.pfe.ai.ai.model.ExamResult;
import com.pfe.ai.ai.model.dto.ExamResultDTO;

import java.util.List;

public interface ExamResultService {
    ExamResult submitExam(ExamResultDTO examResult);
    List<ExamResult> getAllExamResults();
    List<Exam> getSubmittedExams();
    List<Exam> getAvailableExams();
    List<ExamResult> getResultsOfExam(Long examId);

   List<ExamResult> getSubmittedExamsOfStudent(Long studentId);
   List<ExamResult> getSubmittedExamsByTeacher(Long teacherId);
   List<ExamResult> getSubmittedExamsByExamId(Long examId);
}
