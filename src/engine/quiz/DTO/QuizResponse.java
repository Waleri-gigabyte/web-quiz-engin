package engine.quiz.DTO;

import java.util.List;

public record QuizResponse(
        int id,
        String title,
        String text,
        List<String> options
) {
}
