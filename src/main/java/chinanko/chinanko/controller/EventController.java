package chinanko.chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.EventRequest;
import chinanko.chinanko.dto.EventResponse;
import chinanko.chinanko.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT })
@Tag(name = "Event Management", description = "APIs for managing events with cascading addresses.")
public class EventController {

    private final EventService service;

    @PostMapping
    @Operation(summary = "Create a new event (with addresses)")
    public ResponseEntity<EventResponse> create(@Validated @RequestBody EventRequest request) {
        EventResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/events/" + created.getIdEvent()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an event by ID")
    public ResponseEntity<EventResponse> update(@PathVariable Integer id, @Validated @RequestBody EventRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID")
    public ResponseEntity<EventResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // --- Filtros ---

    @GetMapping("/filter/town-type")
    @Operation(summary = "Get events by Town and Type (Paginated)")
    public List<EventResponse> getByTownAndType(
            @RequestParam Integer townId,
            @RequestParam Integer typeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTownAndType(townId, typeId, page, pageSize);
    }

    @GetMapping("/search/postal-code")
    @Operation(summary = "Get events by Address Postal Code")
    public List<EventResponse> getByPostalCode(@RequestParam String postalCode) {
        return service.getByPostalCode(postalCode);
    }

    @GetMapping("/search/street")
    @Operation(summary = "Search events by Address Street Name")
    public List<EventResponse> getByStreet(@RequestParam String street) {
        return service.getByStreet(street);
    }

    @GetMapping("/filter/town")
    @Operation(summary = "Get events by Town (Paginated)")
    public List<EventResponse> getByTown(
            @RequestParam Integer townId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTown(townId, page, pageSize);
    }

    @GetMapping("/filter/town-state")
    @Operation(summary = "Get events by Town and State (Paginated)")
    public List<EventResponse> getByTownAndState(
            @RequestParam Integer townId,
            @RequestParam Integer stateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTownAndState(townId, stateId, page, pageSize);
    }
}