package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfProductResponse;

public interface TypeOfProductService {
    
    // Métodos de lectura (Read-Only)
    
    TypeOfProductResponse getById(Integer id);
    
    List<TypeOfProductResponse> getAll();
    
    TypeOfProductResponse getByName(String typeName);
}