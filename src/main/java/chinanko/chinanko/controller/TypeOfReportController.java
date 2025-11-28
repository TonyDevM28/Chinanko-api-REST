package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TypeOfReportResponse;
import chinanko.chinanko.service.TypeOfReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/types-of-reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET }) // Solo permitimos GET
@Tag(name = "Type of Report Management", 
     description = "APIs for retrieving report types (Read-Only Catalog). Controller Author: Antony Daniel Muñoz Leal")
public class TypeOfReportController {

    private final TypeOfReportService service;

    @GetMapping
    @Operation(summary = "Get all available types of reports")
    public List<TypeOfReportResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific type of report by its ID")
    public TypeOfReportResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a specific type of report by its name")
    public TypeOfReportResponse getByName(@PathVariable String name) {
        return service.getByName(name);
    }
}