package com.pfe.ai.ai.service;

import com.pfe.ai.ai.model.Answer;
import com.pfe.ai.ai.model.Question;
import com.pfe.ai.ai.model.dto.AnswerDto;

import java.util.List;

public interface CommunityService {
    List<Question> getAllQuestions();
    Question addQuestion(Question question, Long userId);
    Answer addAnswer(Long questionId, String content, Long userId);
    List<AnswerDto> getAnswersOfQuestion(Long questionId);

    Question getQuestionById(Long questionId);
}
