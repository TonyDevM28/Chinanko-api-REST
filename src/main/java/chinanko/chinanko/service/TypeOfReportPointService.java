package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfReportPointResponse;

public interface TypeOfReportPointService {

    TypeOfReportPointResponse getById(Integer id);

    List<TypeOfReportPointResponse> getAll();
}