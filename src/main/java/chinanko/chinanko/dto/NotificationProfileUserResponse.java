package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationProfileUserResponse {

    @JsonProperty("id_notification_user")
    private Integer idNotificationUser;

    @JsonProperty("description")
    private String description; // Viene de la notificación padre

    @JsonProperty("type")
    private String typeName;

    @JsonProperty("is_read")
    private Boolean isRead; // El estado true/false

    @JsonProperty("creator_name")
    private String creatorName;
}