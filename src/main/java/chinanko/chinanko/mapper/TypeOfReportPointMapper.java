package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfReportPointResponse;
import chinanko.chinanko.model.TypeOfReportPoint;

@Component
public class TypeOfReportPointMapper {

    public static TypeOfReportPointResponse toResponse(TypeOfReportPoint entity) {
        if (entity == null) return null;

        return TypeOfReportPointResponse.builder()
                .idTypeOfReportPoint(entity.getIdTypeOfReportPoint())
                .type(entity.getType())
                .build();
    }
}