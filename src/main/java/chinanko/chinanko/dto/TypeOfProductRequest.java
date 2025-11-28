package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfProductRequest {

    @NotBlank(message = "Type name is required.")
    @Size(max = 50, message = "Type name cannot exceed 50 characters.")
    @JsonProperty("type_name")
    private String type;
}