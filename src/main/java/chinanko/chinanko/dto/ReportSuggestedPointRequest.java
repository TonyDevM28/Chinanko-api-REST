package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportSuggestedPointRequest {

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Type of Report ID is required")
    @JsonProperty("type_report_id")
    private Integer typeOfReportPointId;

    @NotNull(message = "Suggested Point ID is required")
    @JsonProperty("suggested_point_id")
    private Integer suggestedPointId;

    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Integer userId;
}