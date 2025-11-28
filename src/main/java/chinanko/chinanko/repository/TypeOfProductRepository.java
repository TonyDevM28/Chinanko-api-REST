package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.TypeOfProduct;

@Repository
public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct, Integer> {

    /**
     * Busca un tipo de producto por su nombre.
     */
    Optional<TypeOfProduct> findByType(String type);
}