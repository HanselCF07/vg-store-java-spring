package com.example.vg_store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.vg_store.service.PermissionService;
import com.example.vg_store.service.VideoGameDeveloperService;
import com.example.vg_store.dto.VideoGameDeveloperDTO;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/vg-hc-store/protected/data/video-game-developers")
public class VideoGameDeveloperController {

    private final VideoGameDeveloperService developerService;
    private final PermissionService permissionService;

    public VideoGameDeveloperController(VideoGameDeveloperService developerService,
                                        PermissionService permissionService) {
        this.developerService = developerService;
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        return ResponseEntity.ok(developerService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameDeveloperDTO developer = developerService.getActiveById(id);
        if (developer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Developer no encontrado o inactivo"));
        }

        return ResponseEntity.ok(developer);
    }


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody VideoGameDeveloperDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        if (dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (dto.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }

        VideoGameDeveloperDTO nuevo = developerService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Integer id, @RequestBody VideoGameDeveloperDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameDeveloperDTO actualizado = developerService.update(id, dto);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Developer no encontrado"));
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        boolean eliminado = developerService.delete(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Developer no encontrado"));
        }

        return ResponseEntity.noContent().build();
    }
}
