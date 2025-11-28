package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfReportResponse;

public interface TypeOfReportService {
    
    // Solo métodos de lectura (Read-Only)
    
    TypeOfReportResponse getById(Integer id);
    
    List<TypeOfReportResponse> getAll();
    
    TypeOfReportResponse getByName(String typeName);
}