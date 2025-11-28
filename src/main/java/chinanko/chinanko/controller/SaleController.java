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

import chinanko.chinanko.dto.SaleRequest;
import chinanko.chinanko.dto.SaleResponse;
import chinanko.chinanko.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT })
@Tag(name = "Sales Management", description = "APIs for managing sales transactions. Controller Author: Antony Daniel Muñoz Leal")
public class SaleController {

    private final SaleService service;

    @PostMapping
    @Operation(summary = "Create a new sale transaction")
    public ResponseEntity<SaleResponse> create(@Validated @RequestBody SaleRequest request) {
        SaleResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/sales/" + created.getIdSale()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a sale transaction by ID")
    public SaleResponse update(@PathVariable Integer id, @Validated @RequestBody SaleRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sale by ID")
    public SaleResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // --- Filtros ---

    @GetMapping("/filter/interest-point")
    @Operation(summary = "Get sales by Interest Point (Paginated)")
    public List<SaleResponse> getByInterestPoint(
            @RequestParam Integer interestPointId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByInterestPoint(interestPointId, page, pageSize);
    }

    @GetMapping("/filter/interest-point-type")
    @Operation(summary = "Get sales by Interest Point and Product Type (Paginated)")
    public List<SaleResponse> getByInterestPointAndType(
            @RequestParam Integer interestPointId,
            @RequestParam Integer typeProductId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByInterestPointAndProductType(interestPointId, typeProductId, page, pageSize);
    }

    @GetMapping("/filter/interest-point-product")
    @Operation(summary = "Get sales by Interest Point and Specific Product (Paginated)")
    public List<SaleResponse> getByInterestPointAndProduct(
            @RequestParam Integer interestPointId,
            @RequestParam Integer productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByInterestPointAndProduct(interestPointId, productId, page, pageSize);
    }
}