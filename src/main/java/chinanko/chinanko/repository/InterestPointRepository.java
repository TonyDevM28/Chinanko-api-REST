package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.InterestPoint;

@Repository
public interface InterestPointRepository extends JpaRepository<InterestPoint, Integer> {

    // ACTUALIZADO: El nombre del método coincide con la propiedad 'nameInterestPoint'
    Optional<InterestPoint> findByNameInterestPoint(String nameInterestPoint);

    Page<InterestPoint> findByTown_IdTown(Integer townId, Pageable pageable);

    Page<InterestPoint> findByTown_IdTownAndTypeOfInterestPoint_IdTypeInterestPoint(
            Integer townId, Integer typeId, Pageable pageable);
}