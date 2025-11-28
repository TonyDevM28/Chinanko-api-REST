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

import chinanko.chinanko.dto.SuggestedPointRequest;
import chinanko.chinanko.dto.SuggestedPointResponse;
import chinanko.chinanko.service.SuggestedPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT })
@Tag(name = "Suggested Point Management", description = "APIs for managing user suggested points. Controller Author: Antony Daniel Muñoz Leal")
public class SuggestedPointController {

    private final SuggestedPointService service;

    @PostMapping
    @Operation(summary = "Create a new Suggested Point (with images)")
    public ResponseEntity<SuggestedPointResponse> create(@Validated @RequestBody SuggestedPointRequest request) {
        SuggestedPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/suggested-points/" + created.getIdSuggestedPoint()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Suggested Point by ID")
    public SuggestedPointResponse update(@PathVariable Integer id, @Validated @RequestBody SuggestedPointRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Suggested Point by ID")
    public SuggestedPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/filter/town")
    @Operation(summary = "Get Suggested Points by Town (Paginated)")
    public List<SuggestedPointResponse> getByTown(
            @RequestParam Integer townId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTown(townId, page, pageSize);
    }

    @GetMapping("/filter/town-type")
    @Operation(summary = "Get Suggested Points by Town and Type (Paginated)")
    public List<SuggestedPointResponse> getByTownAndType(
            @RequestParam Integer townId,
            @RequestParam Integer typeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTownAndType(townId, typeId, page, pageSize);
    }

    @GetMapping("/filter/town-state")
    @Operation(summary = "Get Suggested Points by Town and State (Paginated)")
    public List<SuggestedPointResponse> getByTownAndState(
            @RequestParam Integer townId,
            @RequestParam Integer stateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByTownAndState(townId, stateId, page, pageSize);
    }
}