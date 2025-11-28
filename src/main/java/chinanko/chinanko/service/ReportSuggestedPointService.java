package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ReportSuggestedPointRequest;
import chinanko.chinanko.dto.ReportSuggestedPointResponse;

public interface ReportSuggestedPointService {

    ReportSuggestedPointResponse create(ReportSuggestedPointRequest request);

    ReportSuggestedPointResponse update(Integer id, ReportSuggestedPointRequest request);

    void delete(Integer id);

    ReportSuggestedPointResponse getById(Integer id);

    List<ReportSuggestedPointResponse> getBySuggestedPoint(Integer suggestedPointId, int page, int pageSize);

    List<ReportSuggestedPointResponse> getByUser(Integer userId, int page, int pageSize);
}