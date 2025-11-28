package chinanko.chinanko.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;
import chinanko.chinanko.model.AddressEvent;
import chinanko.chinanko.model.Event;
import chinanko.chinanko.model.StateOfEvent;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.model.TypeOfEvent;

@Component
public class EventMapper {

    // Nota: Se asume que AddressEventMapper existe y es funcional. 
    // Si no, debes inyectarlo o usar métodos estáticos. Aquí uso estáticos.

    public static Event toEntity(EventRequest r) {
        if (r == null) return null;

        Event event = Event.builder()
                .nameEvent(r.getNameEvent())
                .description(r.getDescription())
                .timeBegin(r.getTimeBegin())
                .timeEnd(r.getTimeEnd())
                .price(r.getPrice())
                .town(Town.builder().idTown(r.getTownId()).build())
                .typeOfEvent(TypeOfEvent.builder().idTypeEvent(r.getTypeOfEventId()).build())
                .stateOfEvent(StateOfEvent.builder().idStateEvent(r.getStateOfEventId()).build())
                .build();

        // Mapeo en Cascada: Direcciones
        if (r.getAddresses() != null && !r.getAddresses().isEmpty()) {
            List<AddressEvent> addresses = r.getAddresses().stream()
                    .map(AddressEventMapper::toEntity)
                    .collect(Collectors.toList());
            
            // Asignación bidireccional (Padre -> Hijos)
            addresses.forEach(addr -> addr.setEvent(event));
            event.setAddressEvent(addresses);
        }

        return event;
    }

    public static EventResponse toResponse(Event e) {
        if (e == null) return null;

        return EventResponse.builder()
                .idEvent(e.getIdEvent())
                .nameEvent(e.getNameEvent())
                .description(e.getDescription())
                .timeBegin(e.getTimeBegin())
                .timeEnd(e.getTimeEnd())
                .price(e.getPrice())
                .townName(e.getTown() != null ? e.getTown().getNameTown() : "Unknown")
                .typeOfEventName(e.getTypeOfEvent() != null ? e.getTypeOfEvent().getType() : "Unknown")
                .stateOfEventName(e.getStateOfEvent() != null ? e.getStateOfEvent().getState() : "Unknown")
                // Mapeo de respuesta de direcciones
                .addresses(e.getAddressEvent() != null 
                        ? e.getAddressEvent().stream().map(AddressEventMapper::toResponse).collect(Collectors.toList()) 
                        : null)
                .build();
    }

    public static void copyToEntity(Event e, EventRequest r) {
        if (e == null || r == null) return;

        e.setNameEvent(r.getNameEvent());
        e.setDescription(r.getDescription());
        e.setTimeBegin(r.getTimeBegin());
        e.setTimeEnd(r.getTimeEnd());
        e.setPrice(r.getPrice());
        e.setTown(Town.builder().idTown(r.getTownId()).build());
        e.setTypeOfEvent(TypeOfEvent.builder().idTypeEvent(r.getTypeOfEventId()).build());
        e.setStateOfEvent(StateOfEvent.builder().idStateEvent(r.getStateOfEventId()).build());

        // Actualización en Cascada: Direcciones
        if (r.getAddresses() != null) {
            // 1. Limpiar lista actual (orphanRemoval borrará de BD)
            if (e.getAddressEvent() != null) {
                e.getAddressEvent().clear();
            } else {
                e.setAddressEvent(new ArrayList<>());
            }

            // 2. Crear nuevas
            List<AddressEvent> newAddresses = r.getAddresses().stream()
                    .map(AddressEventMapper::toEntity)
                    .collect(Collectors.toList());

            // 3. Asignar padre
            newAddresses.forEach(addr -> addr.setEvent(e));

            // 4. Agregar a colección gestionada
            e.getAddressEvent().addAll(newAddresses);
        }
    }
}