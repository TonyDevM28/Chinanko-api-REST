package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfSuggestedPointResponse {

    @JsonProperty("id_type_suggested")
    private Integer idTypeSuggested;

    @JsonProperty("name")
    private String name;
}