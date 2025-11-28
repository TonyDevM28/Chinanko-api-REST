package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.StateOfEventResponse;
import chinanko.chinanko.service.StateOfEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/states-of-events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@Tag(name = "State of Event Management", 
     description = "APIs for retrieving event states (Read-Only Catalog). Controller Author: Antony Daniel Muñoz Leal")
public class StateOfEventController {

    private final StateOfEventService service;

    @GetMapping
    @Operation(summary = "Get all available states of events")
    public List<StateOfEventResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific state of event by its ID")
    public StateOfEventResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a specific state of event by its name")
    public StateOfEventResponse getByName(@PathVariable String name) {
        return service.getByName(name);
    }
}