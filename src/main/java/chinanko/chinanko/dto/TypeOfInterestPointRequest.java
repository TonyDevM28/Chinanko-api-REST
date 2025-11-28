package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfInterestPointRequest {

    @NotBlank(message = "The name cannot be empty.")
    @Size(max = 100, message = "The name cannot exceed 100 characters.")
    @JsonProperty("name_type_interest_point")
    private String nameTypeInterestPoint;
}