package chinanko.chinanko.controller;

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

import chinanko.chinanko.dto.OpinionSuggestedPointRequest;
import chinanko.chinanko.dto.OpinionSuggestedPointResponse;
import chinanko.chinanko.service.OpinionsSuggestedPointsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/opinions-suggested")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Suggested Point Opinions", description = "Opinions with Sentiment Analysis for Suggested Points")
public class OpinionSuggestedPointController {

    // Inyección de la INTERFAZ, no la implementación
    private final OpinionsSuggestedPointsService service;

    @PostMapping
    @Operation(summary = "Create opinion for suggested point (Filters negative sentiment)")
    public ResponseEntity<OpinionSuggestedPointResponse> create(@Validated @RequestBody OpinionSuggestedPointRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update opinion")
    public ResponseEntity<OpinionSuggestedPointResponse> update(@PathVariable Integer id, @Validated @RequestBody OpinionSuggestedPointRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete opinion")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/filter/point")
    @Operation(summary = "Get opinions by Suggested Point (Paginated)")
    public List<OpinionSuggestedPointResponse> getByPoint(
            @RequestParam Integer pointId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getBySuggestedPoint(pointId, page, pageSize);
    }

    @GetMapping("/filter/user")
    @Operation(summary = "Get opinions by User (Paginated)")
    public List<OpinionSuggestedPointResponse> getByUser(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUser(userId, page, pageSize);
    }
}