package chinanko.chinanko.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.NotificationProfileUserResponse;
import chinanko.chinanko.dto.NotificationRequest;
import chinanko.chinanko.dto.NotificationResponse;
import chinanko.chinanko.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.PATCH })
@Tag(name = "Notification Management", description = "APIs for sending and reading notifications.")
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    @Operation(summary = "Send a notification to multiple users")
    public ResponseEntity<NotificationResponse> create(@Validated @RequestBody NotificationRequest request) {
        NotificationResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/notifications/" + created.getIdNotification()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update notification content (Admin)")
    public ResponseEntity<NotificationResponse> update(@PathVariable Integer id, @RequestBody String description) {
        return ResponseEntity.ok(service.update(id, description));
    }

    @PatchMapping("/{idNotificationUser}/read")
    @Operation(summary = "Mark a specific user notification as read")
    public ResponseEntity<NotificationProfileUserResponse> markAsRead(@PathVariable Integer idNotificationUser) {
        return ResponseEntity.ok(service.markAsRead(idNotificationUser));
    }

    @GetMapping("/admin/{id}")
    @Operation(summary = "Get generic notification info (Admin)")
    public NotificationResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // --- Filtros para el Usuario ---

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all notifications for a user (Inbox)")
    public List<NotificationProfileUserResponse> getByUser(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUser(userId, page, pageSize);
    }

    @GetMapping("/user/{userId}/state")
    @Operation(summary = "Filter user notifications by state (Read/Unread)")
    public List<NotificationProfileUserResponse> getByUserAndState(
            @PathVariable Integer userId,
            @RequestParam Boolean state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUserAndState(userId, state, page, pageSize);
    }

    @GetMapping("/user/{userId}/type")
    @Operation(summary = "Filter user notifications by Type")
    public List<NotificationProfileUserResponse> getByUserAndType(
            @PathVariable Integer userId,
            @RequestParam Integer typeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUserAndType(userId, typeId, page, pageSize);
    }
}