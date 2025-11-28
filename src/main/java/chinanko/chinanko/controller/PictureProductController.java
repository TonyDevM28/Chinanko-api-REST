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

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;
import chinanko.chinanko.service.PictureProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/picture-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Product Images Management", description = "APIs for managing images of products. Controller Author: Antony Daniel Muñoz Leal")
public class PictureProductController {

    private final PictureProductService service;

    @PostMapping
    @Operation(summary = "Create a new image for a product")
    public ResponseEntity<PictureProductResponse> create(@Validated @RequestBody PictureProductRequest request) {
        PictureProductResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/picture-products/" + created.getIdPictureProduct()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an image by ID")
    public PictureProductResponse update(@PathVariable Integer id, @Validated @RequestBody PictureProductRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get image by ID")
    public PictureProductResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an image by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/filter/product")
    @Operation(summary = "Get all images for a specific Product ID")
    public List<PictureProductResponse> getByProductId(@RequestParam Integer productId) {
        return service.getByProductId(productId);
    }
}