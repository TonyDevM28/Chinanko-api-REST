package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateSuggestedPointResponse {

    @JsonProperty("id_state_suggested")
    private Integer idStateSuggested;

    @JsonProperty("state")
    private String state;
}