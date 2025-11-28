package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateOfEventResponse {

    @JsonProperty("id of the state event")
    private Integer idStateEvent;

    @JsonProperty("state name")
    private String state;
}