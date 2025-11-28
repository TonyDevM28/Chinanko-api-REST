package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.StateSuggestedPointResponse;
import chinanko.chinanko.service.StateSuggestedPointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/states-suggested-points")
@RequiredArgsConstructor
@Validated // Necesario para validar RequestParams
@CrossOrigin(origins = "*", methods = { org.springframework.web.bind.annotation.RequestMethod.GET })
@Tag(name = "State Suggested Point Management", description = "APIs for retrieving suggested point states. Controller Author: Antony Daniel Muñoz Leal")
public class StateSuggestedPointController {

    private final StateSuggestedPointService service;

    @GetMapping
    @Operation(summary = "Get all states")
    public List<StateSuggestedPointResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get state by ID")
    public StateSuggestedPointResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/search/{state}")
    @Operation(summary = "Get states by name value")
    public List<StateSuggestedPointResponse> getByStateValue(
            @PathVariable String state) {
        return service.getByStateValue(state);
    }
}