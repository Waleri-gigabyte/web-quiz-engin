package engine.quiz;

import engine.quiz.DTO.AnswerResponse;
import engine.quiz.DTO.NewQuizRequest;
import engine.quiz.DTO.QuizResponse;
import engine.quiz.DTO.SolveQuizRequest;
import engine.quizCompletionHistory.QuizHistoryResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizzesController {

    private final QuizService quizService;

    public QuizzesController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz (
            @Valid @RequestBody NewQuizRequest request
    ){
        QuizResponse response = quizService.saveNewQuiz(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerResponse> postAnswer (
            @PathVariable Integer id,
            @Valid @RequestBody SolveQuizRequest request
    ){
        return ResponseEntity.ok(quizService.getAnswer(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuiz (
            @PathVariable Integer id
    ){
        QuizResponse response = quizService.getQuizById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<QuizResponse>> getAll (
            @RequestParam(name = "page", defaultValue = "0") int page
    ){
        return ResponseEntity.ok(quizService.getAll(page));
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<QuizHistoryResponse>> history (
            @RequestParam int page
    ){
        return ResponseEntity.ok(quizService.getAllHistory(page));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteQuiz(
            @PathVariable Integer id
    ){
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
