package com.pfe.ai.ai.model.dto;

import com.pfe.ai.ai.model.Exam;

import java.util.List;

public record ExamWithQuestions(
        Exam exam,
        List<ExamQuestionDTO> questions
        
) {
}
