package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogResponse {

    @JsonProperty("id_catalog")
    private Integer idCatalog;

    @JsonProperty("name_catalog")
    private String nameCatalog;

    @JsonProperty("description")
    private String description;

    @JsonProperty("interest_point_name")
    private String interestPointName;
    
    // Puedes agregar aquí info del producto si lo deseas
}