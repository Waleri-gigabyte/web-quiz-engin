package engine.quiz.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record NewQuizRequest(
        @NotBlank String title,
        @NotBlank String text,
        @NotNull @Size(min = 2) List<String> options,
        List<Integer> answer
) {

}
