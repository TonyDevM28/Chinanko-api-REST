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

import chinanko.chinanko.dto.ProductRequest;
import chinanko.chinanko.dto.ProductResponse;
import chinanko.chinanko.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Product Management", description = "APIs for managing products. Controller Author: Antony Daniel Muñoz Leal")
public class ProductController {

    private final ProductService service;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductResponse> create(@Validated @RequestBody ProductRequest request) {
        ProductResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/products/" + created.getIdProduct()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product by ID")
    public ProductResponse update(@PathVariable Integer id, @Validated @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ProductResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // --- Filtros ---

    @GetMapping("/filter/catalog")
    @Operation(summary = "Get products by Catalog (Paginated)")
    public List<ProductResponse> getByCatalog(
            @RequestParam Integer catalogId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByCatalog(catalogId, page, pageSize);
    }

    @GetMapping("/filter/catalog-type")
    @Operation(summary = "Get products by Catalog and Type (Paginated)")
    public List<ProductResponse> getByCatalogAndType(
            @RequestParam Integer catalogId,
            @RequestParam Integer typeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByCatalogAndType(catalogId, typeId, page, pageSize);
    }

    @GetMapping("/search/name")
    @Operation(summary = "Get product by Name and Catalog")
    public ProductResponse getByNameAndCatalog(
            @RequestParam String name,
            @RequestParam Integer catalogId) {
        return service.getByNameAndCatalog(name, catalogId);
    }
}