package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ReportProfileUserRequest;
import chinanko.chinanko.dto.ReportProfileUserResponse;
import chinanko.chinanko.model.ProfileUser;
import chinanko.chinanko.model.ReportProfileUser;
import chinanko.chinanko.model.TypeOfReport;

@Component
public class ReportProfileUserMapper {

    public static ReportProfileUser toEntity(ReportProfileUserRequest r) {
        if (r == null)
            return null;

        // Se asume que ReportProfileUser tiene la anotación @Builder
        return ReportProfileUser.builder()
                .description(r.getDescription())
                // Creamos instancias "shell" (solo con ID) para las relaciones
                .typeOfReport(TypeOfReport.builder().idTypeReport(r.getTypeOfReportId()).build())
                .profileUser(ProfileUser.builder().idProfileUser(r.getProfileUserId()).build())
                .build();
    }

    public static ReportProfileUserResponse toResponse(ReportProfileUser entity) {
        if (entity == null)
            return null;

        return ReportProfileUserResponse.builder()
                .idReportProfile(entity.getIdReportProfile())
                .description(entity.getDescription())
                // Validamos que la relación no sea nula antes de pedir el nombre para evitar NullPointerException
                .typeOfReportName(entity.getTypeOfReport() != null ? entity.getTypeOfReport().getType() : null) 
                // Concatenamos nombre y apellido para el nombre del usuario
                .profileUserName(entity.getProfileUser() != null 
                    ? entity.getProfileUser().getFirstName() + " " + entity.getProfileUser().getLastName() 
                    : null)
                .build();
    }

    public static void copyToEntity(ReportProfileUser entity, ReportProfileUserRequest r) {
        if (r == null || entity == null)
            return;

        entity.setDescription(r.getDescription());
        
        // Actualizamos las relaciones creando nuevos objetos con los nuevos IDs
        entity.setTypeOfReport(TypeOfReport.builder().idTypeReport(r.getTypeOfReportId()).build());
        entity.setProfileUser(ProfileUser.builder().idProfileUser(r.getProfileUserId()).build());
    }
}