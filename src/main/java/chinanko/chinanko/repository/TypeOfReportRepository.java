package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfReport;

@Repository
public interface TypeOfReportRepository extends JpaRepository<TypeOfReport, Integer> {

    /**
     * Busca un tipo de reporte por su nombre exacto (case sensitive o insensitive según config de BD).
     */
    Optional<TypeOfReport> findByType(String type);
    
    /**
     * Verifica si existe un tipo con ese nombre (útil para validaciones antes de crear).
     */
    boolean existsByType(String type);
}