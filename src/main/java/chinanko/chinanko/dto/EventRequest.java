package chinanko.chinanko.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventRequest {

    @NotBlank(message = "Event name is required")
    @Size(max = 100)
    @JsonProperty("name_event")
    private String nameEvent;

    @Size(max = 255)
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Start time is required")
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("time_begin")
    private LocalDate timeBegin;

    @NotNull(message = "End time is required")
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("time_end")
    private LocalDate timeEnd;

    @Min(value = 0)
    @JsonProperty("price")
    private float price;

    @NotNull
    @JsonProperty("town_id")
    private Integer townId;

    @NotNull
    @JsonProperty("type_event_id")
    private Integer typeOfEventId;

    @NotNull
    @JsonProperty("state_event_id")
    private Integer stateOfEventId;

    // Lista de direcciones para guardar en cascada
    @Valid
    @JsonProperty("addresses")
    private List<AddressEventRequest> addresses;
}
