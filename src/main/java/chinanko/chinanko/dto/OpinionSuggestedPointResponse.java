package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionSuggestedPointResponse {
    
    @JsonProperty("id_opinion")
    private Integer idOpinion;
    
    @JsonProperty("opinion")
    private String opinion;
    
    @JsonProperty("sentiment_score")
    private BigDecimal sentimentScore;
    
    @JsonProperty("sentiment_type")
    private String sentimentType;
    
    @JsonProperty("user_name")
    private String userName;
    
    @JsonProperty("suggested_point_name")
    private String suggestedPointName;
}