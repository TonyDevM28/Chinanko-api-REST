package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.OpinionInterestPoint;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionInterestPoint, Integer> {

    // Obtener opiniones por punto de interés
    Page<OpinionInterestPoint> findByInterestPoint_IdInterestPoint(Integer interestPointId, Pageable pageable);

    // Obtener opiniones por usuario
    Page<OpinionInterestPoint> findByProfileUser_IdProfileUser(Integer userId, Pageable pageable);
}