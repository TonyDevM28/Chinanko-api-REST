package chinanko.chinanko.dto;

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
public class InterestPointRequest {

    @NotBlank(message = "Name of interest point is required.")
    @Size(max = 255, message = "Name cannot exceed 255 characters.")
    @JsonProperty("name_interest_point")
    private String nameInterestPoint;

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Type of Interest Point ID is required.")
    @JsonProperty("type_of_interest_point_id")
    private Integer typeOfInterestPointId;

    @NotNull(message = "Town ID is required.")
    @JsonProperty("town_id")
    private Integer townId;

    // Dirección anidada (Validada y mapeada)
    @Valid
    @JsonProperty("address")
    private AddressInterestPointRequest address;

    // Imágenes anidadas (Validada y mapeada)
    @Valid
    @JsonProperty("images")
    private List<ImageInterestPointRequest> images;
}