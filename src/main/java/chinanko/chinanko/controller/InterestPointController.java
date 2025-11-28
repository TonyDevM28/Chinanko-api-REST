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

import chinanko.chinanko.dto.InterestPointRequest;
import chinanko.chinanko.dto.InterestPointResponse;
import chinanko.chinanko.service.InterestPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Interest Point Management", description = "APIs for managing points of interest. Controller Author: Antony Daniel Muñoz Leal")
public class InterestPointController {

    private final InterestPointService service;

    @PostMapping
    @Operation(summary = "Create a new Interest Point")
    public ResponseEntity<InterestPointResponse> create(@Validated @RequestBody InterestPointRequest request) {
        InterestPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/interest-points/" + created.getIdInterestPoint()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Interest Point by ID")
    public InterestPointResponse update(@PathVariable Integer id, @Validated @RequestBody InterestPointRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Interest Point by ID")
    public InterestPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get Interest Point by Name")
    public InterestPointResponse getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    // --- Filtros Paginados ---

    @GetMapping("/filter/town")
    @Operation(summary = "Get Interest Points by Town (Paginated)")
    public List<InterestPointResponse> getByTown(
            @RequestParam Integer townId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTown(townId, page, pageSize);
    }

    @GetMapping("/filter/town-type")
    @Operation(summary = "Get Interest Points by Town and Type (Paginated)")
    public List<InterestPointResponse> getByTownAndType(
            @RequestParam Integer townId,
            @RequestParam Integer typeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTownAndType(townId, typeId, page, pageSize);
    }
}