package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.Exam;
import com.pfe.ai.ai.model.ExamQuestion;
import com.pfe.ai.ai.model.Option;
import com.pfe.ai.ai.model.dto.ExamQuestionDTO;

import java.util.List;

public interface ExamService {
    List<ExamQuestion> getQuestionsOfExam(Long examId);

    Exam createExam(Exam newExam, Long courseId, List<ExamQuestionDTO> questions);

    Exam getExamById(Long examId);

    Exam submitExam(ExamQuestionDTO question, Long examId);

    ExamQuestion addResponseToQuestion(Long questionId, Option option);

    List<Exam> getAllExams();
    List<Exam> getTeacherExams(Long teacherId);
}
