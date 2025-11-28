package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.StateSuggestedPointResponse;
import chinanko.chinanko.model.StateSuggestedPoint;

@Component
public class StateSuggestedPointMapper {

    public static StateSuggestedPointResponse toResponse(StateSuggestedPoint entity) {
        if (entity == null) return null;

        return StateSuggestedPointResponse.builder()
                .idStateSuggested(entity.getIdStateSuggested())
                .state(entity.getState())
                .build();
    }
}