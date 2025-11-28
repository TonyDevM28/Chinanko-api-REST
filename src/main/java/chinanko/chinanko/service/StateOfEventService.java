package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.StateOfEventResponse;

public interface StateOfEventService {
    
    // Métodos de lectura (Catálogo estático)

    StateOfEventResponse getById(Integer id);

    List<StateOfEventResponse> getAll();

    StateOfEventResponse getByName(String stateName);
}