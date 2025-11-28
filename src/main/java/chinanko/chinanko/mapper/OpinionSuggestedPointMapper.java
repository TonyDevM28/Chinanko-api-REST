package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.OpinionSuggestedPointResponse;
import chinanko.chinanko.model.OpinionsSuggestedPoints;

@Component
public class OpinionSuggestedPointMapper {

    public static OpinionSuggestedPointResponse toResponse(OpinionsSuggestedPoints e) {
        if (e == null) return null;

        return OpinionSuggestedPointResponse.builder()
                .idOpinion(e.getIdOpinionSuggestedPoint())
                .opinion(e.getOpinion())
                .sentimentScore(e.getPolarity())
                .sentimentType(e.getOfOpinionsSuggestedPoint() != null ? e.getOfOpinionsSuggestedPoint().getType() : "Unknown")
                .userName(e.getProfileUser() != null ? e.getProfileUser().getFirstName() : "Anonymous")
                .suggestedPointName(e.getSuggestedPoint() != null ? e.getSuggestedPoint().getName() : "Unknown")
                .build();
    }
}