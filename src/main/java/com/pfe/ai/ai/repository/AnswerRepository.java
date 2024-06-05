package com.pfe.ai.ai.repository;

import com.pfe.ai.ai.model.Answer;
import com.pfe.ai.ai.model.dto.AnswerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<AnswerDto> findByQuestionId(Long questionId);
}
