package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfSuggestedPointRequest {

    @NotBlank(message = "The type name cannot be empty.")
    @Size(max = 100, message = "The type name cannot exceed 100 characters.")
    @JsonProperty("name")
    private String name;
}