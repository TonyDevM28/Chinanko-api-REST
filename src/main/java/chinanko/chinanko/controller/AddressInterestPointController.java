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

import chinanko.chinanko.dto.AddressInterestPointRequest;
import chinanko.chinanko.dto.AddressInterestPointResponse;
import chinanko.chinanko.service.AddressInterestPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Address Interest Point Management", description = "APIs for managing interest point addresses. Controller Author: Antony Daniel Muñoz Leal")
public class AddressInterestPointController {

    private final AddressInterestPointService service;

    @PostMapping
    @Operation(summary = "Create a new address for an interest point")
    public ResponseEntity<AddressInterestPointResponse> create(@Validated @RequestBody AddressInterestPointRequest request) {
        AddressInterestPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/address-interest-points/" + created.getIdAddressInterest()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an address by ID")
    public AddressInterestPointResponse update(@PathVariable Integer id, @Validated @RequestBody AddressInterestPointRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID")
    public AddressInterestPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an address by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // --- Filtros ---

    @GetMapping("/search/street")
    @Operation(summary = "Search addresses by street name")
    public List<AddressInterestPointResponse> searchByStreet(@RequestParam String street) {
        return service.searchByStreetName(street);
    }

    @GetMapping("/filter/postal-code")
    @Operation(summary = "Get addresses by Postal Code (Paginated)")
    public List<AddressInterestPointResponse> getByPostalCode(
            @RequestParam String postalCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByPostalCode(postalCode, page, pageSize);
    }

    @GetMapping("/filter/town")
    @Operation(summary = "Get addresses by Town ID (Paginated)")
    public List<AddressInterestPointResponse> getByTown(
            @RequestParam Integer townId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTownId(townId, page, pageSize);
    }
}