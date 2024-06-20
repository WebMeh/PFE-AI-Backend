package com.pfe.ai.ai.controller;

import com.pfe.ai.ai.model.Answer;
import com.pfe.ai.ai.model.Question;
import com.pfe.ai.ai.model.dto.AnswerDto;
import com.pfe.ai.ai.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {
    private final CommunityService communityService;

    // Get all questions asked on the community
    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        List<Question> questions = communityService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long questionId) {
        Question question = communityService.getQuestionById(questionId);
        if(question == null)
            return ResponseEntity.ok("Question Not Found with id : "+questionId);
        return ResponseEntity.ok(question);
    }

    // Ask new question
    @PostMapping("/ask/{userId}")
    public ResponseEntity<?> addQuestion(@RequestBody Question question, @PathVariable Long userId) {
        Question savedQuestion = communityService.addQuestion(question, userId);
        if( savedQuestion == null)
            return ResponseEntity.ok("User Not Found with id:"+userId);
        return ResponseEntity.ok(savedQuestion);
    }

    @PostMapping("/answer")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
        Answer savedAnswer = communityService.addAnswer(
                answerDto.questionId(), answerDto.content(), answerDto.userId()
        );

        if (savedAnswer == null)
            return ResponseEntity.ok("Failed to answer");
        return ResponseEntity.ok(savedAnswer);

    }

    // Get answers of a question
    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> getAnswersOfQuestion(@PathVariable Long questionId){
        return ResponseEntity.ok(
                communityService.getAnswersOfQuestion(questionId)
        );
    }
}
