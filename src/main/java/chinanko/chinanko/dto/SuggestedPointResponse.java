package chinanko.chinanko.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuggestedPointResponse {

    @JsonProperty("id_suggested_point")
    private Integer idSuggestedPoint;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("longitude")
    private BigDecimal longitude;

    @JsonProperty("latitude")
    private BigDecimal latitude;

    @JsonProperty("type_name")
    private String typeName;

    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("town_name")
    private String townName;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("images")
    private List<ImageSuggestedPointResponse> images;
}