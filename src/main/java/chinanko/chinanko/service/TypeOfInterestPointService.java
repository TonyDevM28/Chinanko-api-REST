package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfInterestPointResponse;

public interface TypeOfInterestPointService {
    
    // Métodos de lectura
    TypeOfInterestPointResponse getById(Integer id);
    
    List<TypeOfInterestPointResponse> getAll();
}