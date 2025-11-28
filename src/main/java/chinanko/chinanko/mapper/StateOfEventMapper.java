package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.StateOfEventRequest;
import chinanko.chinanko.dto.StateOfEventResponse;
import chinanko.chinanko.model.StateOfEvent;

@Component
public class StateOfEventMapper {

    public static StateOfEvent toEntity(StateOfEventRequest r) {
        if (r == null)
            return null;

        return StateOfEvent.builder()
                .state(r.getState())
                .build();
    }

    public static StateOfEventResponse toResponse(StateOfEvent entity) {
        if (entity == null)
            return null;

        return StateOfEventResponse.builder()
                .idStateEvent(entity.getIdStateEvent())
                .state(entity.getState())
                .build();
    }

    public static void copyToEntity(StateOfEvent entity, StateOfEventRequest r) {
        if (r == null || entity == null)
            return;

        entity.setState(r.getState());
    }
}