package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfInterestPoint;

@Repository
public interface TypeOfInterestPointRepository extends JpaRepository<TypeOfInterestPoint, Integer> {
    // No se requieren métodos extra por el momento
}