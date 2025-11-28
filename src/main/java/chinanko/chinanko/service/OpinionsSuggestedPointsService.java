package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.OpinionSuggestedPointRequest;
import chinanko.chinanko.dto.OpinionSuggestedPointResponse;

public interface OpinionsSuggestedPointsService {

    OpinionSuggestedPointResponse create(OpinionSuggestedPointRequest request);

    OpinionSuggestedPointResponse update(Integer id, OpinionSuggestedPointRequest request);

    void delete(Integer id);

    List<OpinionSuggestedPointResponse> getBySuggestedPoint(Integer pointId, int page, int pageSize);

    List<OpinionSuggestedPointResponse> getByUser(Integer userId, int page, int pageSize);
}