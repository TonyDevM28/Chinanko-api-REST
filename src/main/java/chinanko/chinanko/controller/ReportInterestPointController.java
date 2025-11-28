package chinanko.chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.ReportInterestPointRequest;
import chinanko.chinanko.dto.ReportInterestPointResponse;
import chinanko.chinanko.service.ReportInterestPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reports-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        org.springframework.web.bind.annotation.RequestMethod.GET,
        org.springframework.web.bind.annotation.RequestMethod.POST })
@Tag(name = "Interest Point Reports Management", description = "APIs for reporting issues on interest points.")
public class ReportInterestPointController {

    private final ReportInterestPointService service;

    @PostMapping
    @Operation(summary = "Create a new report for an interest point")
    public ResponseEntity<ReportInterestPointResponse> create(@Validated @RequestBody ReportInterestPointRequest request) {
        ReportInterestPointResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/reports-interest-points/" + created.getIdReportInterestPoint()))
                .body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get report by ID")
    public ReportInterestPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/filter/interest-point")
    @Operation(summary = "Get reports by Interest Point (Paginated)")
    public List<ReportInterestPointResponse> getByInterestPoint(
            @RequestParam Integer interestPointId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByInterestPoint(interestPointId, page, pageSize);
    }

    @GetMapping("/filter/user")
    @Operation(summary = "Get reports by User (Paginated)")
    public List<ReportInterestPointResponse> getByUser(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUser(userId, page, pageSize);
    }
}