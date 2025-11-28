package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PictureProductResponse {

    @JsonProperty("id_picture_product")
    private Integer idPictureProduct;

    @JsonProperty("url")
    private String url;

    @JsonProperty("product_name")
    private String productName;
}