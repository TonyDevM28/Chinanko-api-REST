package chinanko.chinanko.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterestPointResponse {

    @JsonProperty("id_interest_point")
    private Integer idInterestPoint;

    @JsonProperty("name_interest_point")
    private String nameInterestPoint;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type_of_interest_point")
    private String typeOfInterestPoint;

    @JsonProperty("town_name")
    private String townName;

    @JsonProperty("address")
    private AddressInterestPointResponse address;

    @JsonProperty("images")
    private List<ImageInterestPointResponse> images;
}