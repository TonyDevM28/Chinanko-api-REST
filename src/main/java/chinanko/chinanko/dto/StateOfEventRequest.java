package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateOfEventRequest {

    @NotBlank(message = "The state name cannot be empty.")
    @Size(max = 50, message = "The state name cannot exceed 50 characters.")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "The state name can only contain letters and spaces.")
    @JsonProperty("state name")
    private String state;
}