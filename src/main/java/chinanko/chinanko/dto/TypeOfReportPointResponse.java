package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfReportPointResponse {

    @JsonProperty("id_type_report_point")
    private Integer idTypeOfReportPoint;

    @JsonProperty("type")
    private String type;
}