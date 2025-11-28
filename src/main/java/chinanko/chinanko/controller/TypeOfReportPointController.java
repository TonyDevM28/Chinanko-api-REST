package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TypeOfReportPointResponse;
import chinanko.chinanko.service.TypeOfReportPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/types-of-reports-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { org.springframework.web.bind.annotation.RequestMethod.GET })
@Tag(name = "Type of Report Point Management", description = "APIs for retrieving types of reports for points (Read-Only).")
public class TypeOfReportPointController {

    private final TypeOfReportPointService service;

    @GetMapping
    @Operation(summary = "Get all types of report points")
    public List<TypeOfReportPointResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get type by ID")
    public TypeOfReportPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}