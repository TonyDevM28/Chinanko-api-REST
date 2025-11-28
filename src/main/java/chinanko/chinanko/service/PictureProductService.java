package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;

public interface PictureProductService {

    PictureProductResponse create(PictureProductRequest request);

    PictureProductResponse update(Integer id, PictureProductRequest request);

    PictureProductResponse getById(Integer id);

    void delete(Integer id);

    // Obtener galería de imágenes de un producto
    List<PictureProductResponse> getByProductId(Integer productId);
}