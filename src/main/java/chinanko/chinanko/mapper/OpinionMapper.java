package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.OpinionResponse;
import chinanko.chinanko.model.OpinionInterestPoint;

@Component
public class OpinionMapper {

    public static OpinionResponse toResponse(OpinionInterestPoint e) {
        if (e == null) return null;

        return OpinionResponse.builder()
                .idOpinion(e.getIdOpinionInterestPoint())
                .opinion(e.getOpinion())
                .sentiment(e.getTypeOfOpinion() != null ? e.getTypeOfOpinion().getPolarithy() : "Unknown")
                .confidenceScore(e.getPolarity())
                // Asumiendo que ProfileUser tiene un método getFirstName o similar
                .userName(e.getProfileUser() != null ? e.getProfileUser().getFirstName() : "Anonymous")
                .interestPointName(e.getInterestPoint() != null ? e.getInterestPoint().getNameInterestPoint() : "Unknown")
                .build();
    }
}