package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {
    // Return list of questions of a given exam
    List<ExamQuestion> findByExamId(Long examId);
}
