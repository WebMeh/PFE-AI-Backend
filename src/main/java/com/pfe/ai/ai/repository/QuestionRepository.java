package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
