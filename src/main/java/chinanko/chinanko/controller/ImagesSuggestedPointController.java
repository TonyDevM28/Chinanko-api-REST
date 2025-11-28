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
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.ImageSuggestedPointRequest;
import chinanko.chinanko.dto.ImageSuggestedPointResponse;
import chinanko.chinanko.service.ImagesSuggestedPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/images-suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Suggested Point Images Management", description = "APIs for managing images of suggested points. Controller Author: Antony Daniel Muñoz Leal")
public class ImagesSuggestedPointController {

    private final ImagesSuggestedPointService service;

    @PostMapping
    @Operation(summary = "Create a new image for a suggested point")
    public ResponseEntity<ImageSuggestedPointResponse> create(@Validated @RequestBody ImageSuggestedPointRequest request) {
        ImageSuggestedPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/images-suggested-points/" + created.getIdImageSuggested()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an image by ID")
    public ImageSuggestedPointResponse update(@PathVariable Integer id, @Validated @RequestBody ImageSuggestedPointRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get image by ID")
    public ImageSuggestedPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an image by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/filter/suggested-point/{suggestedPointId}")
    @Operation(summary = "Get all images for a specific Suggested Point ID")
    public List<ImageSuggestedPointResponse> getBySuggestedPoint(@PathVariable Integer suggestedPointId) {
        return service.getBySuggestedPointId(suggestedPointId);
    }
}