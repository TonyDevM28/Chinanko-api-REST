package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionSuggestedPointRequest {

    @NotBlank(message = "Opinion text is required")
    @Size(max = 1000, message = "Opinion cannot exceed 1000 characters")
    @JsonProperty("opinion")
    private String opinion;

    @NotNull(message = "Suggested Point ID is required")
    @JsonProperty("suggested_point_id")
    private Integer suggestedPointId;

    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Integer userId;
}
