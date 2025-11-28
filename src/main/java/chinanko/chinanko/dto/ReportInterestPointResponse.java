package chinanko.chinanko.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportInterestPointResponse {

    @JsonProperty("id_report")
    private Integer idReportInterestPoint;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date_report")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateReport;

    @JsonProperty("type_report")
    private String typeOfReportPointName;

    @JsonProperty("interest_point")
    private String interestPointName;

    @JsonProperty("user_name")
    private String userName;
}
