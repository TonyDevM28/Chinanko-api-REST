package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.SuggestedPointRequest;
import chinanko.chinanko.dto.SuggestedPointResponse;

public interface SuggestedPointService {

    SuggestedPointResponse create(SuggestedPointRequest request);

    SuggestedPointResponse update(Integer id, SuggestedPointRequest request);

    SuggestedPointResponse getById(Integer id);

    // Filtros
    List<SuggestedPointResponse> getByTown(Integer townId, int page, int pageSize);

    List<SuggestedPointResponse> getByTownAndType(Integer townId, Integer typeId, int page, int pageSize);

    List<SuggestedPointResponse> getByTownAndState(Integer townId, Integer stateId, int page, int pageSize);
}