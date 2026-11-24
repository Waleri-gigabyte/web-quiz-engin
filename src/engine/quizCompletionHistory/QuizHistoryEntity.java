package engine.quizCompletionHistory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
public class QuizHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private  Integer userId;
    @NotNull
    private Integer quizId;
    @CreationTimestamp
    private Instant completedAt;

    public QuizHistoryEntity (
            Integer userId,
            Integer quizId
    ){
        this.userId = userId;
        this.quizId = quizId;
    }

    public QuizHistoryEntity(){}

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }
}
