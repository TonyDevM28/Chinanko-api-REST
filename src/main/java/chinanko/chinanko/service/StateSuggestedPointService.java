package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.StateSuggestedPointResponse;

public interface StateSuggestedPointService {
    
    // Obtener por ID
    StateSuggestedPointResponse getById(Integer id);
    
    // Obtener todos
    List<StateSuggestedPointResponse> getAll();
    
    // Obtener por valor del estado (texto)
    List<StateSuggestedPointResponse> getByStateValue(String state);
}