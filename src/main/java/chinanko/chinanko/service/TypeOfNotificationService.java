package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.TypeOfNotificationResponse;

public interface TypeOfNotificationService {

    TypeOfNotificationResponse getById(Integer id);

    List<TypeOfNotificationResponse> getAll();
}