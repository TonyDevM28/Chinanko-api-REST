package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    // 1. Obtener ventas por punto de interés paginado
    Page<Sale> findDistinctBySalesProducts_Product_Catalog_InterestPoint_IdInterestPoint(
            Integer interestPointId, Pageable pageable);

    // 2. Obtener ventas por punto de interés y tipo de producto paginado
    Page<Sale> findDistinctBySalesProducts_Product_Catalog_InterestPoint_IdInterestPointAndSalesProducts_Product_TypeOfProduct_IdTypeProduct(
            Integer interestPointId, Integer typeProductId, Pageable pageable);

    // 3. Obtener ventas por punto de interés y producto específico paginado
    Page<Sale> findDistinctBySalesProducts_Product_Catalog_InterestPoint_IdInterestPointAndSalesProducts_Product_IdProduct(
            Integer interestPointId, Integer productId, Pageable pageable);
}