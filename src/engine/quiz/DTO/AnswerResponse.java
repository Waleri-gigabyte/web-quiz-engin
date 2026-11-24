package engine.quiz.DTO;

public record AnswerResponse(
        boolean success,
        String feedback
) {
}
