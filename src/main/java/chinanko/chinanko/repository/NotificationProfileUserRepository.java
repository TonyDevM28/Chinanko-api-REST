package chinanko.chinanko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.NotificationProfileUser;

@Repository
public interface NotificationProfileUserRepository extends JpaRepository<NotificationProfileUser, Integer> {

    // 1. Obtención por perfil de usuario, paginado
    Page<NotificationProfileUser> findByUser_IdUser(Integer userId, Pageable pageable); // Ajustar IdUser según tu entidad User

    // 2. Obtención por perfil de usuario y estado (Leído/No leído), paginado
    Page<NotificationProfileUser> findByUser_IdUserAndState(Integer userId, Boolean state, Pageable pageable);

    // 3. Obtención por perfil de usuario y tipo, paginado
    // Navegación: NotificationProfileUser -> Notification -> TypeOfNotification -> id
    Page<NotificationProfileUser> findByUser_IdUserAndNotification_TypeOfNotification_IdTypeNotification(
            Integer userId, Integer typeId, Pageable pageable);
}