package engine.quiz.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SolveQuizRequest(
        @NotNull List<Integer> answer
) {
}
