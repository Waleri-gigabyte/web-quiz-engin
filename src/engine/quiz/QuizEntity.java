package engine.quiz;

import engine.authorization.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> options;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> answer;

    public QuizEntity(
            UserEntity user,
            String title,
            String text,
            List<String> options,
            List<Integer> answer
    ) {
        this.user = user;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    protected QuizEntity(){}

    public UserEntity getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
