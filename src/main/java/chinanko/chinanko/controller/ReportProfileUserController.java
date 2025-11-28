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

import chinanko.chinanko.dto.ReportProfileUserRequest;
import chinanko.chinanko.dto.ReportProfileUserResponse;
import chinanko.chinanko.service.ReportProfileUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/report-profile-users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Report Profile User Management", 
     description = "APIs for managing user reports. Controller Author: Antony Daniel Muñoz Leal")
public class ReportProfileUserController {

    // Injects the ReportProfileUser service
    private final ReportProfileUserService service;

    @PostMapping // Handles POST requests
    @Operation(summary = "Create a new report for a user")
    public ResponseEntity<ReportProfileUserResponse> create(@Validated @RequestBody ReportProfileUserRequest request) {
        // Creates a new Report
        ReportProfileUserResponse created = service.create(request);
        
        // Returns 201 Created status with the new resource URI
        return ResponseEntity.created(URI.create("/api/v1/report-profile-users/" + created.getIdReportProfile()))
                .body(created);
    }

    @GetMapping // Handles GET requests
    @Operation(summary = "Get all reports")
    public List<ReportProfileUserResponse> getAll() {
        // Returns a list of all reports
        return service.getAll();
    }

    @GetMapping("/{id}") // Handles GET requests by ID
    @Operation(summary = "Get a report by its ID")
    public ReportProfileUserResponse getById(@PathVariable Integer id) {
        // Finds a Report by its ID
        return service.getById(id);
    }

    @GetMapping("/user/{userId}") // Handles GET requests by User ID
    @Operation(summary = "Get all reports associated with a specific user")
    public List<ReportProfileUserResponse> getByUserId(@PathVariable Integer userId) {
        // Finds all reports for a specific user
        return service.getByUserId(userId);
    }

    @GetMapping("/type/{typeId}") // Handles GET requests by Type ID
    @Operation(summary = "Get all reports of a specific type")
    public List<ReportProfileUserResponse> getByTypeId(@PathVariable Integer typeId) {
        // Finds all reports of a specific type category
        return service.getByTypeId(typeId);
    }

    @GetMapping("/description/{text}") // Handles GET requests by description search
    @Operation(summary = "Search reports by description text")
    public List<ReportProfileUserResponse> searchByDescription(@PathVariable String text) {
        // Finds reports containing the text in their description
        return service.searchByDescription(text);
    }

    @PutMapping("/{id}") // Handles PUT requests by ID
    @Operation(summary = "Update a report by its ID")
    public ReportProfileUserResponse update(@Validated @PathVariable Integer id, @RequestBody ReportProfileUserRequest request) {
        // Updates an existing Report
        return service.update(id, request);
    }

    @DeleteMapping("/{id}") // Handles DELETE requests by ID
    @Operation(summary = "Delete a report by its ID")
    public void delete(@PathVariable Integer id) {
        // Deletes a Report
        service.delete(id);
    }
}