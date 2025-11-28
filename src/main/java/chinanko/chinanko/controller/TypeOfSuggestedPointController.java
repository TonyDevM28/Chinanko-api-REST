package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TypeOfSuggestedPointResponse;
import chinanko.chinanko.service.TypeOfSuggestedPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/types-of-suggested-points")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET }) // Solo permitimos GET
@Tag(name = "Type of Suggested Point Management", 
     description = "APIs for retrieving suggested point types (Read-Only Catalog). Controller Author: Antony Daniel Muñoz Leal")
public class TypeOfSuggestedPointController {

    private final TypeOfSuggestedPointService service;

    @GetMapping
    @Operation(summary = "Get all available types of suggested points")
    public List<TypeOfSuggestedPointResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific type by its ID")
    public TypeOfSuggestedPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a specific type by its name")
    public TypeOfSuggestedPointResponse getByName(@PathVariable String name) {
        return service.getByName(name);
    }
}