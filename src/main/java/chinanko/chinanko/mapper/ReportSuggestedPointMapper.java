package chinanko.chinanko.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ReportSuggestedPointRequest;
import chinanko.chinanko.dto.ReportSuggestedPointResponse;
import chinanko.chinanko.model.ReportSuggestedPoint;
import chinanko.chinanko.model.SuggestedPoint;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.model.User;

@Component
public class ReportSuggestedPointMapper {

    public static ReportSuggestedPoint toEntity(ReportSuggestedPointRequest r) {
        if (r == null) return null;

        return ReportSuggestedPoint.builder()
                .description(r.getDescription())
                .dateReport(LocalDateTime.now()) // Fecha automática
                .typeOfReportPoint(TypeOfReportPoint.builder().idTypeOfReportPoint(r.getTypeOfReportPointId()).build())
                .suggestedPoint(SuggestedPoint.builder().idSuggestedPoint(r.getSuggestedPointId()).build())
                .user(User.builder().idUser(r.getUserId()).build())
                .build();
    }

    public static ReportSuggestedPointResponse toResponse(ReportSuggestedPoint e) {
        if (e == null) return null;

        return ReportSuggestedPointResponse.builder()
                .idReportSuggestedPoint(e.getIdReportSuggestedPoint())
                .description(e.getDescription())
                .dateReport(e.getDateReport())
                .typeOfReportPointName(e.getTypeOfReportPoint() != null ? e.getTypeOfReportPoint().getType() : "Unknown")
                .suggestedPointName(e.getSuggestedPoint() != null ? e.getSuggestedPoint().getName() : "Unknown")
                .userName(e.getUser() != null ? e.getUser().getUsername() : "Unknown")
                .build();
    }

    // Método auxiliar para actualizar la entidad
    public static void copyToEntity(ReportSuggestedPoint e, ReportSuggestedPointRequest r) {
        if (e == null || r == null) return;

        e.setDescription(r.getDescription());
        // Actualizar relaciones si es necesario
        e.setTypeOfReportPoint(TypeOfReportPoint.builder().idTypeOfReportPoint(r.getTypeOfReportPointId()).build());
        e.setSuggestedPoint(SuggestedPoint.builder().idSuggestedPoint(r.getSuggestedPointId()).build());
        e.setUser(User.builder().idUser(r.getUserId()).build());
        // La fecha de reporte generalmente no se actualiza al editar, se mantiene la original.
    }
}