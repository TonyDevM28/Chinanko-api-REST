package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.StateSuggestedPoint;

@Repository
public interface StateSuggestedPointRepository extends JpaRepository<StateSuggestedPoint, Integer> {

    /**
     * Encuentra estados por su valor de texto.
     */
    List<StateSuggestedPoint> findByState(String state);
}