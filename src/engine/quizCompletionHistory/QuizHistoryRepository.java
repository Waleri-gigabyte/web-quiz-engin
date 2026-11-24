package engine.quizCompletionHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizHistoryRepository extends JpaRepository<QuizHistoryEntity, Long> {

    Page<QuizHistoryEntity> findAllByUserIdOrderByCompletedAtDesc(
            Integer userId,
            Pageable pageable
    );
}
