package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.SuggestedPoint;

@Repository
public interface SuggestedPointRepository extends JpaRepository<SuggestedPoint, Integer> {

    // 1. Obtención por pueblo, paginado
    Page<SuggestedPoint> findByTown_IdTown(Integer townId, Pageable pageable);

    // 2. Obtención por tipo y pueblo, paginado
    Page<SuggestedPoint> findByTown_IdTownAndTypeOfSuggestedPoint_IdTypeSuggested(Integer townId, Integer typeId, Pageable pageable);

    // 3. Obtención por estado del punto sugerido y pueblo, paginado
    Page<SuggestedPoint> findByTown_IdTownAndStateSuggestedPoint_IdStateSuggested(Integer townId, Integer stateId, Pageable pageable);
}