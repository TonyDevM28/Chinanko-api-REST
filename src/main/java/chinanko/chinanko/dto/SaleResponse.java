package chinanko.chinanko.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleResponse {

    @JsonProperty("id_sale")
    private Integer idSale;

    @JsonProperty("total")
    private Long total;
    
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("products")
    private List<SalesProductResponse> products;
}