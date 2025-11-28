package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ImagesSuggestedPoint;

@Repository
public interface ImagesSuggestedPointRepository extends JpaRepository<ImagesSuggestedPoint, Integer> {

    /**
     * Obtiene todas las imágenes asociadas a un punto sugerido específico.
     */
    List<ImagesSuggestedPoint> findBySuggestedPoint_IdSuggestedPoint(Integer suggestedPointId);
}