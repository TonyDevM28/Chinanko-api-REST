package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionResponse {

    @JsonProperty("id_opinion")
    private Integer idOpinion;

    @JsonProperty("opinion")
    private String opinion;

    @JsonProperty("sentiment")
    private String sentiment; // "positive", "neutral"

    @JsonProperty("confidence_score")
    private BigDecimal confidenceScore;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("interest_point_name")
    private String interestPointName;
}