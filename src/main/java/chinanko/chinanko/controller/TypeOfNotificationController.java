package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.TypeOfNotificationResponse;
import chinanko.chinanko.service.TypeOfNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/types-of-notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { org.springframework.web.bind.annotation.RequestMethod.GET })
@Tag(name = "Type of Notification Management", description = "APIs for retrieving types of notifications (Read-Only).")
public class TypeOfNotificationController {

    private final TypeOfNotificationService service;

    @GetMapping
    @Operation(summary = "Get all types of notifications")
    public List<TypeOfNotificationResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get type by ID")
    public TypeOfNotificationResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}