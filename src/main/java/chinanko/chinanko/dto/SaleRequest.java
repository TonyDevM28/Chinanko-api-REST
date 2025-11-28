package chinanko.chinanko.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleRequest {
    // No pedimos el total aquí, se calcula en el backend.

    // NUEVO: ID del usuario obligatorio para la venta
    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Integer userId;

    @NotEmpty(message = "Sale must have at least one product")
    @Valid
    @JsonProperty("products")
    private List<SalesProductRequest> products;
}