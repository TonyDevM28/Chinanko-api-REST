package chinanko.chinanko.controller;

import java.net.URI;

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

import chinanko.chinanko.dto.CatalogRequest;
import chinanko.chinanko.dto.CatalogResponse;
import chinanko.chinanko.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT })
@Tag(name = "Catalog Management", description = "APIs for managing product catalogs. Controller Author: Antony Daniel Muñoz Leal")
public class CatalogController {

    private final CatalogService service;

    @PostMapping
    @Operation(summary = "Create a new catalog")
    public ResponseEntity<CatalogResponse> create(@Validated @RequestBody CatalogRequest request) {
        CatalogResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/catalogs/" + created.getIdCatalog()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a catalog by ID")
    public CatalogResponse update(@PathVariable Integer id, @Validated @RequestBody CatalogRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get catalog by ID")
    public CatalogResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Get catalog by Interest Point (ID or Name)")
    public ResponseEntity<CatalogResponse> getByInterestPoint(
            @RequestParam(required = false) Integer interestPointId,
            @RequestParam(required = false) String interestPointName) {
        
        if (interestPointId != null) {
            return ResponseEntity.ok(service.getByInterestPointId(interestPointId));
        } else if (interestPointName != null) {
            return ResponseEntity.ok(service.getByInterestPointName(interestPointName));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}