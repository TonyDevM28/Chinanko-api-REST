package chinanko.chinanko.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chinanko.chinanko.dto.ProfileUserRequest; // Necesario para crear y actualizar
import chinanko.chinanko.dto.ProfileUserResponse;
import chinanko.chinanko.service.ProfileUserService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileUserController {

    private final ProfileUserService service;

    public ProfileUserController(ProfileUserService service){
        this.service = service;
    }

    // Método 1: Listar todos (list())
    @GetMapping
    public ResponseEntity<List<ProfileUserResponse>> list(){
        return ResponseEntity.ok(service.listAll());
    }

    // Método 2: Obtener por ID (getById(Integer idProfileUser))
    @GetMapping("/{id}")
    public ResponseEntity<ProfileUserResponse> getById(@PathVariable("id") Integer idProfileUser) {
        ProfileUserResponse response = service.getById(idProfileUser);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // Método 3: Crear (create(ProfileUserRequest p))
    @PostMapping
    public ResponseEntity<ProfileUserResponse> create(@RequestBody ProfileUserRequest p) {
        ProfileUserResponse created = service.create(p);
        // Se utiliza HttpStatus.CREATED (201) para indicar una creación exitosa
        return new ResponseEntity<>(created, HttpStatus.CREATED); 
    }

    // Método 4: Actualizar (update(Integer idProfileUser, ProfileUserRequest p))
    @PutMapping("/{id}")
    public ResponseEntity<ProfileUserResponse> update(
            @PathVariable("id") Integer idProfileUser, 
            @RequestBody ProfileUserRequest p) {
        
        ProfileUserResponse updated = service.update(idProfileUser, p);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}