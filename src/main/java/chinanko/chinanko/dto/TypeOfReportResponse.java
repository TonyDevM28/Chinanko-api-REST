package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfReportResponse {

    @JsonProperty("id of the type")
    private Integer idTypeReport;

    @JsonProperty("type name")
    private String type;
}