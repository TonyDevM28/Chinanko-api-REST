package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.OpinionsSuggestedPoints;

@Repository
public interface OpinionsSuggestedPointsRepository extends JpaRepository<OpinionsSuggestedPoints, Integer> {

    // Obtener opiniones por punto sugerido paginadas
    Page<OpinionsSuggestedPoints> findBySuggestedPoint_IdSuggestedPoint(Integer suggestedPointId, Pageable pageable);

    // Obtener opiniones por usuario paginadas
    Page<OpinionsSuggestedPoints> findByProfileUser_IdProfileUser(Integer userId, Pageable pageable);
}