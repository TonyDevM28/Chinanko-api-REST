package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesProductRequest {
    
    @NotNull(message = "Product ID is required")
    @JsonProperty("product_id")
    private Integer productId;

    @NotNull(message = "Quantity is required")
    @Min(1)
    @JsonProperty("quantity")
    private Integer quantity;
}