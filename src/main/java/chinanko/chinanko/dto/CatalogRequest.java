package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogRequest {

    @NotBlank(message = "Catalog name is required.")
    @Size(max = 100, message = "Catalog name cannot exceed 100 characters.")
    @JsonProperty("name_catalog")
    private String nameCatalog;

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Interest Point ID is required.")
    @JsonProperty("interest_point_id")
    private Integer interestPointId;
}