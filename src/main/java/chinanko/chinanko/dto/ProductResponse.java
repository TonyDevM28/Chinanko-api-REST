package chinanko.chinanko.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    @JsonProperty("id_product")
    private Integer idProduct;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("catalog_name")
    private String catalogName;

    @JsonProperty("type_of_product")
    private String typeOfProduct;

    @JsonProperty("images")
    private List<PictureProductResponse> images;
}