package com.pfe.ai.ai.service.impl;

import com.pfe.ai.ai.model.*;
import com.pfe.ai.ai.model.dto.ExamQuestionDTO;
import com.pfe.ai.ai.repository.*;
import com.pfe.ai.ai.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ExamQuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;
    private final CourseRepository courseRepository;
    @Override
    public List<ExamQuestion> getQuestionsOfExam(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    @Override
    public Exam createExam(Exam newExam,Long courseId, List<ExamQuestionDTO> questionDTOS) {
        // Get the course of exam
        Cour course = courseRepository.findById(courseId).orElse(null);
        if (course == null)
            return null;

        // Save the exam
        newExam.setCourse(course);
        newExam.setQuestions(new ArrayList<>());
        Exam savedExam = examRepository.save(newExam);

        for(ExamQuestionDTO questionDTO : questionDTOS) {
            // Create new question
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setText(questionDTO.text());
            examQuestion.setAnswer(questionDTO.answer());
            examQuestion.setOptions(new ArrayList<>());

            // Relation between Exam and question
            examQuestion.setExam(savedExam);
            savedExam.getQuestions().add(examQuestion);

            // Save the question
            ExamQuestion savedQuestion = questionRepository.save(examQuestion);

            // Relation between Question and Option : each question should have 4 options
            for (Option option : questionDTO.options()) {
                    // 1. Save the option after relation with question
                option.setQuestion(savedQuestion);
                Option savedOption = optionRepository.save(option);

                    // 2. Add the saved option to the list of options of the saved question
                savedQuestion.getOptions().add(savedOption);

            }
            // Update the saved question: we added the options
            questionRepository.save(savedQuestion);

        }

        return examRepository.save(savedExam);
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId).orElse(null);
    }

    @Override
    public Exam submitExam(ExamQuestionDTO question, Long examId) {
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam == null)
            return null;

        // Save the question
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExam(exam);
        examQuestion.setText(question.text());
        examQuestion.setAnswer(question.answer());
        questionRepository.save(examQuestion);

        // save the update
        return examRepository.save(exam);

    }

    @Override
    public ExamQuestion addResponseToQuestion(Long questionId, Option option) {
        // Get the question from db
        ExamQuestion question = questionRepository.findById(questionId)
                .orElse(null);
        if (question == null)
            return null;

        // Save the option in db
        option.setQuestion(question);
        optionRepository.save(option);

        // Add the option to the question
        question.getOptions().add(option);
        return questionRepository.save(question);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public List<Exam> getTeacherExams(Long teacherId) {
        User teacher = userRepository.findById(teacherId).orElse(null);
        if(teacher == null)
            return null;
        List<Exam> allExams = examRepository.findAll();
        List<Exam> teacherExams = allExams.stream()
                .filter(exam -> exam.getCourse().getTeacher().getId().equals(teacherId))
                .collect(Collectors.toList());

        return teacherExams;
    }

    ExamQuestion getExamQuestionFromDTO (ExamQuestionDTO examQuestionDTO) {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setId(examQuestionDTO.id());
        examQuestion.setText(examQuestionDTO.text());
        examQuestion.setAnswer(examQuestionDTO.answer());
        return examQuestion;
    }
}
