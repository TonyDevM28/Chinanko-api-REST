package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfReportRequest;
import chinanko.chinanko.dto.TypeOfReportResponse;
import chinanko.chinanko.model.TypeOfReport;

@Component
public class TypeOfReportMapper {

    public static TypeOfReport toEntity(TypeOfReportRequest r) {
        if (r == null)
            return null;

        return TypeOfReport.builder()
                .type(r.getType())
                .build();
    }

    public static TypeOfReportResponse toResponse(TypeOfReport entity) {
        if (entity == null)
            return null;

        return TypeOfReportResponse.builder()
                .idTypeReport(entity.getIdTypeReport())
                .type(entity.getType())
                .build();
    }

    public static void copyToEntity(TypeOfReport entity, TypeOfReportRequest r) {
        if (r == null || entity == null)
            return;

        entity.setType(r.getType());
    }
}