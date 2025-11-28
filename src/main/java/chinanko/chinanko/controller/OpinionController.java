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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.OpinionRequest;
import chinanko.chinanko.dto.OpinionResponse;
import chinanko.chinanko.service.OpinionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/opinions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE })
@Tag(name = "Opinion Management", description = "APIs for managing opinions with Sentiment Analysis. Controller Author: Antony Daniel Muñoz Leal")
public class OpinionController {

    private final OpinionService service;

    @PostMapping
    @Operation(summary = "Create a new opinion (Filters offensive content)")
    public ResponseEntity<OpinionResponse> create(@Validated @RequestBody OpinionRequest request) {
        OpinionResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/opinions/" + created.getIdOpinion()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an opinion")
    public OpinionResponse update(@PathVariable Integer id, @Validated @RequestBody OpinionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an opinion")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/filter/interest-point")
    @Operation(summary = "Get opinions by Interest Point (Paginated)")
    public List<OpinionResponse> getByInterestPoint(
            @RequestParam Integer interestPointId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByInterestPoint(interestPointId, page, pageSize);
    }

    @GetMapping("/filter/user")
    @Operation(summary = "Get opinions by User (Paginated)")
    public List<OpinionResponse> getByUser(
            @RequestParam Integer userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.getByUser(userId, page, pageSize);
    }
}