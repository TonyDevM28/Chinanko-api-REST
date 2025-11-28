package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.SaleRequest;
import chinanko.chinanko.dto.SaleResponse;

public interface SaleService {

    SaleResponse create(SaleRequest request);

    SaleResponse update(Integer id, SaleRequest request);

    SaleResponse getById(Integer id);

    // Filtros Paginados
    List<SaleResponse> getByInterestPoint(Integer interestPointId, int page, int pageSize);

    List<SaleResponse> getByInterestPointAndProductType(Integer interestPointId, Integer typeProductId, int page, int pageSize);

    List<SaleResponse> getByInterestPointAndProduct(Integer interestPointId, Integer productId, int page, int pageSize);
}