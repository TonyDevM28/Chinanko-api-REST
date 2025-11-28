package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfProductResponse {

    @JsonProperty("id_type_product")
    private Integer idTypeProduct;

    @JsonProperty("type_name")
    private String type;
}