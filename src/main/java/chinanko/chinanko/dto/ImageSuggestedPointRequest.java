package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageSuggestedPointRequest {

    @NotBlank(message = "URL cannot be empty.")
    @Size(max = 255, message = "URL cannot exceed 255 characters.")
    @JsonProperty("url")
    private String url;

    @NotNull(message = "Suggested Point ID is required.")
    @JsonProperty("suggested_point_id")
    private Integer suggestedPointId;
}