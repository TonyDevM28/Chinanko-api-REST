package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfSuggestedPoint;

@Repository
public interface TypeOfSuggestedPointRepository extends JpaRepository<TypeOfSuggestedPoint, Integer> {

    /**
     * Busca un tipo de punto sugerido por su nombre exacto.
     */
    Optional<TypeOfSuggestedPoint> findByName(String name);
}