package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfOpinion;

@Repository
public interface TypeOfOpinionRepository extends JpaRepository<TypeOfOpinion, Integer> {
    Optional<TypeOfOpinion> findByPolarithy(String polarithy);
}