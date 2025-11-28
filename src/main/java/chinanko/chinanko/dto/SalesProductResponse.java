package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesProductResponse {
    
    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("unit_price")
    private Long unitPrice;

    @JsonProperty("subtotal")
    private Long subtotal;
}