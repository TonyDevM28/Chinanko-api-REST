package chinanko.chinanko.mapper;

import chinanko.chinanko.dto.UserLoginRequest;
import chinanko.chinanko.dto.UserLoginResponse;
import chinanko.chinanko.model.Role;
import chinanko.chinanko.model.User;

public final class UserLoginMapper {

    /**
     * Convierte una entidad User a un DTO UserLoginResponse.
     * Nota: Los campos 'token' y 'expiresIn' no se mapean aquí
     * porque no son parte de la entidad User. Deben ser
     * asignados en la capa de servicio después de la autenticación.
     */
    public static UserLoginResponse toResponse(User user) {
        if (user == null) return null;

        // 1. Obtener el objeto Role directamente de la entidad User
        Role userRole = user.getRole();

        // 2. Obtener el nombre del rol (String).
        // Asumimos que la clase Role implementa GrantedAuthority y
        // tiene el método getAuthority(), como implicaba tu código original.
        String roleName = null;
        if (userRole != null) {
            roleName = userRole.getAuthority();
        }

        return UserLoginResponse.builder()
                .email(user.getEmail())
                .role(roleName) // Asignar el nombre del rol obtenido
                .build();
    }

    /**
     * Convierte un DTO UserLoginRequest a una entidad User.
     * Este método es útil para crear un objeto User temporal
     * para el proceso de autenticación, no para persistencia
     * (ya que faltan campos como 'nameUser' y 'role').
     */
    public static User toEntity(UserLoginRequest dto) {
        if (dto == null) return null;

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    /**
     * Copia propiedades de un DTO UserLoginRequest a una entidad User existente.
     * (La utilidad de este método en un contexto de 'login' es limitada,
     * pero es sintácticamente correcto).
     */
    public static void copyToEntity(UserLoginRequest dto, User entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}