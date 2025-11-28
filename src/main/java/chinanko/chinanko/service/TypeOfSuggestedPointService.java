package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfSuggestedPointResponse;

public interface TypeOfSuggestedPointService {
    
    // Métodos de lectura (Read-Only)
    
    TypeOfSuggestedPointResponse getById(Integer id);
    
    List<TypeOfSuggestedPointResponse> getAll();
    
    TypeOfSuggestedPointResponse getByName(String name);
}