package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.AddressInterestPointRequest;
import chinanko.chinanko.dto.AddressInterestPointResponse;

public interface AddressInterestPointService {
    
    // CRUD Básico
    AddressInterestPointResponse create(AddressInterestPointRequest request);
    AddressInterestPointResponse update(Integer id, AddressInterestPointRequest request);
    AddressInterestPointResponse getById(Integer id);
    void delete(Integer id);

    // Búsquedas Específicas
    List<AddressInterestPointResponse> getByPostalCode(String postalCode, int page, int pageSize);
    List<AddressInterestPointResponse> getByTownId(Integer townId, int page, int pageSize);
    List<AddressInterestPointResponse> searchByStreetName(String streetName);
}