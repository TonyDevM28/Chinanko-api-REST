package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

    // Buscar por ID del punto de interés
    Optional<Catalog> findByInterestPoint_IdInterestPoint(Integer interestPointId);

    // Buscar por nombre del punto de interés
    Optional<Catalog> findByInterestPoint_NameInterestPoint(String interestPointName);
}