package engine.quiz;

import engine.authorization.UserEntity;
import engine.authorization.UserRepository;
import engine.quiz.DTO.AnswerResponse;
import engine.quiz.DTO.NewQuizRequest;
import engine.quiz.DTO.QuizResponse;
import engine.quiz.DTO.SolveQuizRequest;
import engine.quizCompletionHistory.QuizHistoryEntity;
import engine.quizCompletionHistory.QuizHistoryRepository;
import engine.quizCompletionHistory.QuizHistoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class QuizService {

    private final QuizRepository repository;
    private final UserRepository userRepository;
    private final QuizHistoryRepository quizHistoryRepository;

    public QuizService (
            QuizRepository repository,
            UserRepository userRepository,
            QuizHistoryRepository quizHistoryRepository
    ){
        this.repository = repository;
        this.userRepository =userRepository;
        this.quizHistoryRepository = quizHistoryRepository;
    }

    private QuizEntity findQuizOrThrow(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Quiz not found"
                        ));
    }

    private UserEntity getUserOrTheow(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(
                                "User not found"
                        ));
    }

    private QuizResponse toQuizResponse( QuizEntity entity){
        return new QuizResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getText(),
                entity.getOptions()
                );
    }

    public QuizResponse saveNewQuiz (NewQuizRequest request){
        QuizEntity forSave = new QuizEntity(
                getUserOrTheow(),
                request.title(),
                request.text(),
                request.options(),
                request.answer()
        );
        repository.save(forSave);
        return toQuizResponse(forSave);
    }

    public QuizResponse getQuizById (Integer id) {
        QuizEntity response = findQuizOrThrow(id);
        return toQuizResponse(response);
    }

    public Page<QuizResponse> getAll (int page){
        return  repository.findAll(PageRequest.of(page,10))
                .map(this::toQuizResponse);
    }

    public Page<QuizHistoryResponse> getAllHistory (int page){
        Integer id = getUserOrTheow().getId();
        return quizHistoryRepository
                .findAllByUserIdOrderByCompletedAtDesc(
                        id,
                        PageRequest.of(page, 10)
                )
                .map(h -> new QuizHistoryResponse(
                        h.getQuizId(),
                        h.getCompletedAt()
                ));
    }

    public AnswerResponse getAnswer (Integer id, SolveQuizRequest request) {

        QuizEntity quiz = findQuizOrThrow(id);
        List<Integer> quizAnswers = quiz.getAnswer() == null ? List.of() : quiz.getAnswer();
        List<Integer> answers = request.answer();

        boolean success = new HashSet<>(answers).equals(new HashSet<>(quizAnswers));

        String feedback = "Wrong answer! Please, try again.";

        if (success){
            feedback = "Congratulations, you're right!";
            UserEntity user =getUserOrTheow();
            quizHistoryRepository.save(
                    new QuizHistoryEntity(
                            user.getId(),
                            quiz.getId()
                    )
            );
        }

        return new AnswerResponse(
                success,
                feedback
        );
    }

    public void deleteQuiz (Integer id){

        UserEntity user = getUserOrTheow();
        QuizEntity quiz = findQuizOrThrow(id);
        if (!user.equals(quiz.getUser())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        repository.delete(quiz);
    }
}
