package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ReportSuggestedPoint;

@Repository
public interface ReportSuggestedPointRepository extends JpaRepository<ReportSuggestedPoint, Integer> {

    // Obtener reportes por punto sugerido
    Page<ReportSuggestedPoint> findBySuggestedPoint_IdSuggestedPoint(Integer suggestedPointId, Pageable pageable);

    // Obtener reportes por usuario
    Page<ReportSuggestedPoint> findByUser_IdUser(Integer userId, Pageable pageable);
}