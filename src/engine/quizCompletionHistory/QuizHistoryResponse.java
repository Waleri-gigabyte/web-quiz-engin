package engine.quizCompletionHistory;

import java.time.Instant;

public record QuizHistoryResponse(
        Integer id,
        Instant completedAt
) {
}
