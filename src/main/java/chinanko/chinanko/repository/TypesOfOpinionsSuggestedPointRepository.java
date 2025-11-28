package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypesOfOpinionsSuggestedPoint;

@Repository
public interface TypesOfOpinionsSuggestedPointRepository extends JpaRepository<TypesOfOpinionsSuggestedPoint, Integer> {
    
    // Buscar por tipo ("positive", "neutral", "negative")
    Optional<TypesOfOpinionsSuggestedPoint> findByType(String type);
}