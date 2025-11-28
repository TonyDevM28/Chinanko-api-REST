package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Obtener lista de productos por catálogo paginado
    Page<Product> findByCatalog_IdCatalog(Integer catalogId, Pageable pageable);

    // Obtener lista de productos por catálogo y tipo de producto paginado
    Page<Product> findByCatalog_IdCatalogAndTypeOfProduct_IdTypeProduct(Integer catalogId, Integer typeId, Pageable pageable);

    // Obtener producto por nombre y catálogo
    Optional<Product> findByNameAndCatalog_IdCatalog(String name, Integer catalogId);
}