package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageInterestPointResponse {

    @JsonProperty("id_image")
    private Integer idImageInterestPoint;

    @JsonProperty("url")
    private String url;

    @JsonProperty("interest_point_name")
    private String interestPointName;
}