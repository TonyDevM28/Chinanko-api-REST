package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;
import chinanko.chinanko.model.AddressEvent;
import chinanko.chinanko.model.Event;

@Component
public class AddressEventMapper {

    public static AddressEvent toEntity(AddressEventRequest r) {
        if (r == null) return null;

        return AddressEvent.builder()
                .street(r.getStreet())
                .exteriorNumber(r.getExteriorNumber())
                .interiorNumber(r.getInteriorNumber())
                .neighborhood(r.getNeighborhood())
                .postalCode(r.getPostalCode())
                .latitude(r.getLatitude())
                .longitude(r.getLongitude())
                .event(Event.builder().idEvent(r.getEventId()).build())
                .build();
    }

    public static AddressEventResponse toResponse(AddressEvent e) {
        if (e == null) return null;

        return AddressEventResponse.builder()
                .idAddressEvent(e.getIdAddressEvent())
                .street(e.getStreet())
                .exteriorNumber(e.getExteriorNumber())
                .interiorNumber(e.getInteriorNumber())
                .neighborhood(e.getNeighborhood())
                .postalCode(e.getPostalCode())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .eventName(e.getEvent() != null ? e.getEvent().getNameEvent() : "Unknown")
                .build();
    }

    public static void copyToEntity(AddressEvent e, AddressEventRequest r) {
        if (e == null || r == null) return;

        e.setStreet(r.getStreet());
        e.setExteriorNumber(r.getExteriorNumber());
        e.setInteriorNumber(r.getInteriorNumber());
        e.setNeighborhood(r.getNeighborhood());
        e.setPostalCode(r.getPostalCode());
        e.setLatitude(r.getLatitude());
        e.setLongitude(r.getLongitude());
        e.setEvent(Event.builder().idEvent(r.getEventId()).build());
    }
}
