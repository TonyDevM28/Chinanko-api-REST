package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfSuggestedPointRequest;
import chinanko.chinanko.dto.TypeOfSuggestedPointResponse;
import chinanko.chinanko.model.TypeOfSuggestedPoint;

@Component
public class TypeOfSuggestedPointMapper {

    public static TypeOfSuggestedPoint toEntity(TypeOfSuggestedPointRequest r) {
        if (r == null) return null;

        // Asumiendo que la entidad tiene @Builder o constructor adecuado
        TypeOfSuggestedPoint type = new TypeOfSuggestedPoint();
        type.setName(r.getName());
        return type;
    }

    public static TypeOfSuggestedPointResponse toResponse(TypeOfSuggestedPoint entity) {
        if (entity == null) return null;

        return TypeOfSuggestedPointResponse.builder()
                .idTypeSuggested(entity.getIdTypeSuggested())
                .name(entity.getName())
                .build();
    }
}