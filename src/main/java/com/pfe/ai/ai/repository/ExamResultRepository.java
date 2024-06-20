package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Exam;
import com.pfe.ai.ai.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    List<ExamResult> findByExam(Exam exam);
}
