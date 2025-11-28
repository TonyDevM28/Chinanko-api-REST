package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.NotificationProfileUserResponse;
import chinanko.chinanko.dto.NotificationRequest;
import chinanko.chinanko.dto.NotificationResponse;

public interface NotificationService {

    // Crear notificación (y distribuirla a los usuarios)
    NotificationResponse create(NotificationRequest request);

    // Actualizar contenido de la notificación base
    NotificationResponse update(Integer id, String newDescription);

    // Marcar una notificación de usuario específica como leída
    NotificationProfileUserResponse markAsRead(Integer idNotificationUser);

    // Obtención por ID base
    NotificationResponse getById(Integer id);

    // --- Consultas Personalizadas ---
    List<NotificationProfileUserResponse> getByUser(Integer userId, int page, int pageSize);

    List<NotificationProfileUserResponse> getByUserAndState(Integer userId, Boolean state, int page, int pageSize);

    List<NotificationProfileUserResponse> getByUserAndType(Integer userId, Integer typeId, int page, int pageSize);
}