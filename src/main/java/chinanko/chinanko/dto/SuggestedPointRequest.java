package chinanko.chinanko.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuggestedPointRequest {

    @NotBlank(message = "Name is required.")
    @Size(max = 150, message = "Name cannot exceed 150 characters.")
    @JsonProperty("name")
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    @JsonProperty("description")
    private String description;

    @JsonProperty("longitude")
    private BigDecimal longitude;

    @JsonProperty("latitude")
    private BigDecimal latitude;

    @NotNull(message = "Type ID is required")
    @JsonProperty("type_id")
    private Integer typeId;

    @NotNull(message = "State ID is required")
    @JsonProperty("state_id")
    private Integer stateId;

    @NotNull(message = "Town ID is required")
    @JsonProperty("town_id")
    private Integer townId;

    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Integer userId;

    // Lista de imágenes para creación en cascada
    @Valid
    @JsonProperty("images")
    private List<ImageSuggestedPointRequest> images;
}