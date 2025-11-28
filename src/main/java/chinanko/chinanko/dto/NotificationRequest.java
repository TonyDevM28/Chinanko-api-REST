package chinanko.chinanko.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {

    @NotBlank(message = "Description is required")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Creator User ID is required")
    @JsonProperty("creator_user_id")
    private Integer creatorUserId;

    @NotNull(message = "Type ID is required")
    @JsonProperty("type_notification_id")
    private Integer typeNotificationId;

    // Lista de IDs de los usuarios que recibirán la notificación
    @NotEmpty(message = "Must have at least one recipient")
    @JsonProperty("recipient_user_ids")
    private List<Integer> recipientUserIds;
}