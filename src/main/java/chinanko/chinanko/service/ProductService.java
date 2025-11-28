package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ProductRequest;
import chinanko.chinanko.dto.ProductResponse;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Integer id, ProductRequest request);

    ProductResponse getById(Integer id);

    // Búsquedas Específicas
    List<ProductResponse> getByCatalog(Integer catalogId, int page, int pageSize);

    List<ProductResponse> getByCatalogAndType(Integer catalogId, Integer typeId, int page, int pageSize);

    ProductResponse getByNameAndCatalog(String name, Integer catalogId);
}