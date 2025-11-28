package chinanko.chinanko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.SalesProduct;

@Repository
public interface SalesProductRepository extends JpaRepository<SalesProduct, Integer> {
}