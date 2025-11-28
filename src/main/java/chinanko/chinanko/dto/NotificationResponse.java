package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {

    @JsonProperty("id_notification")
    private Integer idNotification;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String typeName;

    @JsonProperty("creator_name")
    private String creatorName;

    @JsonProperty("recipients_count")
    private int recipientsCount;
}
