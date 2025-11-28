package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.ReportProfileUser;

@Repository
public interface ReportProfileUserRepository extends JpaRepository<ReportProfileUser, Integer> {

    /**
     * Encuentra todos los reportes asociados a un usuario específico.
     * Útil para ver el historial de reportes de una persona.
     */
    List<ReportProfileUser> findByProfileUser_IdProfileUser(Integer idProfileUser);

    /**
     * Encuentra todos los reportes de un tipo específico (ej. "Comportamiento inapropiado").
     * Útil para filtrar por categorías de problemas.
     */
    // CORRECTO
    List<ReportProfileUser> findByTypeOfReport_IdTypeReport(Integer id);

    /**
     * Busca reportes cuya descripción contenga cierto texto (ignorando mayúsculas/minúsculas).
     * Útil para búsquedas administrativas o de auditoría.
     */
    List<ReportProfileUser> findByDescriptionContainingIgnoreCase(String description);

    /**
     * Encuentra reportes de un usuario específico filtrados también por tipo.
     */
    // CORRECTO (usando idTypeReport)
    List<ReportProfileUser> findByProfileUser_IdProfileUserAndTypeOfReport_IdTypeReport(Integer idProfile, Integer idType);
}