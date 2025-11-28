package chinanko.chinanko.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventResponse {

    @JsonProperty("id_event")
    private Integer idEvent;

    @JsonProperty("name_event")
    private String nameEvent;

    @JsonProperty("description")
    private String description;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("time_begin")
    private LocalDate timeBegin;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("time_end")
    private LocalDate timeEnd;

    @JsonProperty("price")
    private float price;

    @JsonProperty("town_name")
    private String townName;

    @JsonProperty("type_event")
    private String typeOfEventName;

    @JsonProperty("state_event")
    private String stateOfEventName;

    @JsonProperty("addresses")
    private List<AddressEventResponse> addresses;
}
