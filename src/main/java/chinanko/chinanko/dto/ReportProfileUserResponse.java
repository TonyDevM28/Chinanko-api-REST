package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportProfileUserResponse {

    @JsonProperty("id of the report")
    private Integer idReportProfile;

    @JsonProperty("description")
    private String description;

    /**
     * The name or label of the report type (derived from TypeOfReport entity).
     */
    @JsonProperty("type of report name")
    private String typeOfReportName;

    /**
     * The full name or identifier of the user profile (derived from ProfileUser entity).
     */
    @JsonProperty("name of the profile user")
    private String profileUserName;
}