package chinanko.chinanko.service;

import chinanko.chinanko.dto.CatalogRequest;
import chinanko.chinanko.dto.CatalogResponse;

public interface CatalogService {
    
    CatalogResponse create(CatalogRequest request);
    
    CatalogResponse update(Integer id, CatalogRequest request);
    
    CatalogResponse getById(Integer id);
    
    CatalogResponse getByInterestPointId(Integer interestPointId);
    
    CatalogResponse getByInterestPointName(String interestPointName);
}