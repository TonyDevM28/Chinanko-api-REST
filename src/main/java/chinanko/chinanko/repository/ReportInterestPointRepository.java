package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ReportInterestPoint;

@Repository
public interface ReportInterestPointRepository extends JpaRepository<ReportInterestPoint, Integer> {

    // Obtener reportes por punto de interés
    Page<ReportInterestPoint> findByInterestPoint_IdInterestPoint(Integer interestPointId, Pageable pageable);

    // Obtener reportes hechos por un usuario
    Page<ReportInterestPoint> findByUser_IdUser(Integer userId, Pageable pageable); // Ajustar IdUser según tu entidad User
    
    // Obtener reportes por tipo
List<ReportInterestPoint> findByTypeOfReportPoint_IdTypeOfReportPoint(Integer id);
}