package chinanko.chinanko.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required.")
    @Size(max = 100, message = "Product name cannot exceed 100 characters.")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Price is required.")
    @Min(value = 0, message = "Price cannot be negative.")
    @JsonProperty("price")
    private Long price;

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Stock is required.")
    @Min(value = 0, message = "Stock cannot be negative.")
    @JsonProperty("stock")
    private Integer stock;

    @NotNull(message = "Catalog ID is required.")
    @JsonProperty("catalog_id")
    private Integer catalogId;

    @NotNull(message = "Type of Product ID is required.")
    @JsonProperty("type_of_product_id")
    private Integer typeOfProductId;

    // Lista opcional de imágenes para creación en cascada
    @Valid
    @JsonProperty("images")
    private List<PictureProductRequest> images;
}