package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfInterestPointRequest;
import chinanko.chinanko.dto.TypeOfInterestPointResponse;
import chinanko.chinanko.model.TypeOfInterestPoint;

@Component
public class TypeOfInterestPointMapper {

    public static TypeOfInterestPoint toEntity(TypeOfInterestPointRequest r) {
        if (r == null) return null;

        return TypeOfInterestPoint.builder()
                .nameTypeInterestPoint(r.getNameTypeInterestPoint())
                .build();
    }

    public static TypeOfInterestPointResponse toResponse(TypeOfInterestPoint entity) {
        if (entity == null) return null;

        return TypeOfInterestPointResponse.builder()
                .idTypeInterestPoint(entity.getIdTypeInterestPoint())
                .nameTypeInterestPoint(entity.getNameTypeInterestPoint())
                .build();
    }

    public static void copyToEntity(TypeOfInterestPoint entity, TypeOfInterestPointRequest r) {
        if (r == null || entity == null) return;

        entity.setNameTypeInterestPoint(r.getNameTypeInterestPoint());
    }
}