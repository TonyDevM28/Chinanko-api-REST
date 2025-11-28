package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.PictureProduct;

@Repository
public interface PictureProductRepository extends JpaRepository<PictureProduct, Integer> {

    /**
     * Obtiene todas las imágenes de un producto específico.
     */
    List<PictureProduct> findByProduct_IdProduct(Integer productId);
}