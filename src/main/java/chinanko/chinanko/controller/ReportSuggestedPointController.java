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

import chinanko.chinanko.dto.ReportSuggestedPointRequest;
import chinanko.chinanko.dto.ReportSuggestedPointResponse;
import chinanko.chinanko.service.ReportSuggestedPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reports-suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Suggested Point Reports Management", description = "APIs for reporting issues on suggested points.")
public class ReportSuggestedPointController {

    private final ReportSuggestedPointService service;

    @PostMapping
    @Operation(summary = "Create a new report for a suggested point")
    public ResponseEntity<ReportSuggestedPointResponse> create(@Validated @RequestBody ReportSuggestedPointRequest request) {
        ReportSuggestedPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/reports-suggested-points/" + created.getIdReportSuggestedPoint()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing report by ID")
    public ResponseEntity<ReportSuggestedPointResponse> update(@PathVariable Integer id, @Validated @RequestBody ReportSuggestedPointRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a report by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get report by ID")
    public ReportSuggestedPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/filter/suggested-point")
    @Operation(summary = "Get reports by Suggested Point (Paginated)")
    public List<ReportSuggestedPointResponse> getBySuggestedPoint(
            @RequestParam Integer suggestedPointId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getBySuggestedPoint(suggestedPointId, page, pageSize);
    }

    @GetMapping("/filter/user")
    @Operation(summary = "Get reports by User (Paginated)")
    public List<ReportSuggestedPointResponse> getByUser(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUser(userId, page, pageSize);
    }
}