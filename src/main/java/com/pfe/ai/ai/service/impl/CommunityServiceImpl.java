package com.pfe.ai.ai.service.impl;

import com.pfe.ai.ai.model.Answer;
import com.pfe.ai.ai.model.Question;
import com.pfe.ai.ai.model.User;
import com.pfe.ai.ai.model.dto.AnswerDto;
import com.pfe.ai.ai.repository.AnswerRepository;
import com.pfe.ai.ai.repository.QuestionRepository;
import com.pfe.ai.ai.repository.UserRepository;
import com.pfe.ai.ai.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question addQuestion(Question question, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) return null;
        question.setCreatedDate(new Date());
        question.setAnswers(new ArrayList<>());
        question.setUser(user);
        return questionRepository.save(question);
    }


    // Répondre à une question
    @Override
    public Answer addAnswer(Long questionId, String content, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Question question = questionRepository.findById(questionId).orElse(null);
        if(user == null || question == null) return null;
        Answer answer = new Answer();
        answer.setUser(user);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        answer.setCreatedDate(currentDate.format(dateTimeFormatter));
        answer.setQuestion(question);
        answer.setContent(content);
        return answerRepository.save(answer);
    }

    @Override
    public List<AnswerDto> getAnswersOfQuestion(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }
}
