package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TypeOfInterestPointResponse;
import chinanko.chinanko.service.TypeOfInterestPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/types-of-interest-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@Tag(name = "Type of Interest Point Management", 
     description = "APIs for retrieving types of interest points (Read-Only Catalog). Controller Author: Antony Daniel Muñoz Leal")
public class TypeOfInterestPointController {

    private final TypeOfInterestPointService service;

    @GetMapping
    @Operation(summary = "Get all types of interest points")
    public List<TypeOfInterestPointResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific type by ID")
    public TypeOfInterestPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}