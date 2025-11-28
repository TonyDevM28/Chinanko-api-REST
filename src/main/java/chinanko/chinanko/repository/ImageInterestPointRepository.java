package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ImageInterestPoint;

@Repository
public interface ImageInterestPointRepository extends JpaRepository<ImageInterestPoint, Integer> {

    /**
     * Obtiene todas las imágenes asociadas a un punto de interés.
     */
    List<ImageInterestPoint> findByInterestPoint_IdInterestPoint(Integer interestPointId);
}