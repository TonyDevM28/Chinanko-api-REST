package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ReportProfileUserRequest;
import chinanko.chinanko.dto.ReportProfileUserResponse;

public interface ReportProfileUserService {

    // CRUD Básico
    ReportProfileUserResponse create(ReportProfileUserRequest request);
    ReportProfileUserResponse getById(Integer id);
    List<ReportProfileUserResponse> getAll();
    ReportProfileUserResponse update(Integer id, ReportProfileUserRequest request);
    void delete(Integer id);

    // Métodos específicos basados en el Repository
    List<ReportProfileUserResponse> getByUserId(Integer userId);
    List<ReportProfileUserResponse> getByTypeId(Integer typeId);
    List<ReportProfileUserResponse> searchByDescription(String text);
}