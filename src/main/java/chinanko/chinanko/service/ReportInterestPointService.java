package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ReportInterestPointRequest;
import chinanko.chinanko.dto.ReportInterestPointResponse;

public interface ReportInterestPointService {

    ReportInterestPointResponse create(ReportInterestPointRequest request);

    ReportInterestPointResponse getById(Integer id);

    List<ReportInterestPointResponse> getByInterestPoint(Integer interestPointId, int page, int pageSize);

    List<ReportInterestPointResponse> getByUser(Integer userId, int page, int pageSize);
}