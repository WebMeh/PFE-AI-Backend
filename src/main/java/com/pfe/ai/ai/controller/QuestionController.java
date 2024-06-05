package com.pfe.ai.ai.controller;

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

    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        List<Question> questions = communityService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/ask/{userId}")
    public ResponseEntity<?> addQuestion(@RequestBody Question question, @PathVariable Long userId) {
        return ResponseEntity.ok(communityService.addQuestion(question, userId));
    }

    @PostMapping("/answer")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
        //return ResponseEntity.ok("question id = "+question+", user id = "+userId+"content = "+content);

        return ResponseEntity.ok(communityService.addAnswer(
                answerDto.questionId(), answerDto.content(), answerDto.userId()
        ));

    }

    // Get answers of a question
    @GetMapping("/question/{questionId}")
    public ResponseEntity<?> getAnswersOfQuestion(@PathVariable Long questionId){
        return ResponseEntity.ok(
                communityService.getAnswersOfQuestion(questionId)
        );
    }
}
