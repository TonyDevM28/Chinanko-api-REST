package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageSuggestedPointResponse {

    @JsonProperty("id_image_suggested")
    private Integer idImageSuggested;

    @JsonProperty("url")
    private String url;

    @JsonProperty("suggested_point_name")
    private String suggestedPointName;
}