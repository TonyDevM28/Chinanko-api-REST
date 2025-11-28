package chinanko.chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;
import chinanko.chinanko.service.AddressEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address-events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Address Event Management", description = "APIs for managing event addresses.")
public class AddressEventController {

    private final AddressEventService service;

    @PostMapping
    @Operation(summary = "Create a new address for an event")
    public ResponseEntity<AddressEventResponse> create(@Validated @RequestBody AddressEventRequest request) {
        AddressEventResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/address-events/" + created.getIdAddressEvent()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an address by ID")
    public ResponseEntity<AddressEventResponse> update(@PathVariable Integer id, @Validated @RequestBody AddressEventRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID")
    public ResponseEntity<AddressEventResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an address by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // --- Filtros Paginados ---

    @GetMapping("/filter/postal-code")
    @Operation(summary = "Get addresses by Postal Code (Paginated)")
    public List<AddressEventResponse> getByPostalCode(
            @RequestParam String postalCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByPostalCode(postalCode, page, pageSize);
    }

    @GetMapping("/filter/event")
    @Operation(summary = "Get addresses by Event ID (Paginated)")
    public List<AddressEventResponse> getByEvent(
            @RequestParam Integer eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByEvent(eventId, page, pageSize);
    }

    @GetMapping("/filter/town")
    @Operation(summary = "Get addresses by Town ID (Paginated via Event)")
    public List<AddressEventResponse> getByTown(
            @RequestParam Integer townId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTown(townId, page, pageSize);
    }
}