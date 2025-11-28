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

import chinanko.chinanko.dto.ImageInterestPointRequest;
import chinanko.chinanko.dto.ImageInterestPointResponse;
import chinanko.chinanko.service.ImageInterestPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/images-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Interest Point Images Management", description = "APIs for managing images of interest points. Controller Author: Antony Daniel Muñoz Leal")
public class ImageInterestPointController {

    private final ImageInterestPointService service;

    @PostMapping
    @Operation(summary = "Create a new image for an interest point")
    public ResponseEntity<ImageInterestPointResponse> create(@Validated @RequestBody ImageInterestPointRequest request) {
        ImageInterestPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/images-interest-points/" + created.getIdImageInterestPoint()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an image by ID")
    public ImageInterestPointResponse update(@PathVariable Integer id, @Validated @RequestBody ImageInterestPointRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get image by ID")
    public ImageInterestPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an image by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/filter/interest-point")
    @Operation(summary = "Get all images for a specific Interest Point ID")
    public List<ImageInterestPointResponse> getByInterestPoint(@RequestParam Integer interestPointId) {
        return service.getByInterestPointId(interestPointId);
    }
}