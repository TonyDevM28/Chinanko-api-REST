package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TypeOfProductResponse;
import chinanko.chinanko.service.TypeOfProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/types-of-products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET }) // Solo permitimos GET
@Tag(name = "Type of Product Management", 
     description = "APIs for retrieving product types (Read-Only Catalog). Controller Author: Antony Daniel Muñoz Leal")
public class TypeOfProductController {

    private final TypeOfProductService service;

    @GetMapping
    @Operation(summary = "Get all available types of products")
    public List<TypeOfProductResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific type of product by its ID")
    public TypeOfProductResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a specific type of product by its name")
    public TypeOfProductResponse getByName(@PathVariable String name) {
        return service.getByName(name);
    }
}