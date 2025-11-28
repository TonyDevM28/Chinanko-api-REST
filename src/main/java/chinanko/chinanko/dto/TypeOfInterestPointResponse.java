package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfInterestPointResponse {

    @JsonProperty("id_type_interest_point")
    private Integer idTypeInterestPoint;

    @JsonProperty("name_type_interest_point")
    private String nameTypeInterestPoint;
}