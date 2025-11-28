package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeOfNotificationResponse {

    @JsonProperty("id_type_notification")
    private Integer idTypeNotification;

    @JsonProperty("type")
    private String type;
}