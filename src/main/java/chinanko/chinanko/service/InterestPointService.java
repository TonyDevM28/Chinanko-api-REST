package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.InterestPointRequest;
import chinanko.chinanko.dto.InterestPointResponse;

public interface InterestPointService {

    // CRUD Básico
    InterestPointResponse create(InterestPointRequest request);
    InterestPointResponse update(Integer id, InterestPointRequest request);
    InterestPointResponse getById(Integer id);
    InterestPointResponse getByName(String name);

    // Filtros Paginados
    List<InterestPointResponse> getByTown(Integer townId, int page, int pageSize);
    List<InterestPointResponse> getByTownAndType(Integer townId, Integer typeId, int page, int pageSize);
}