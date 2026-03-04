package com.example.vg_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.vg_store.service.VideoGameDeveloperService;
import com.example.vg_store.dto.VideoGameDeveloperDTO;

import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/vg-hc-store/data/public/video-game-developers")
public class PublicVideoGameDeveloperController {

    private final VideoGameDeveloperService developerService;

    public PublicVideoGameDeveloperController(VideoGameDeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validateAccess(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

        return ResponseEntity.ok(developerService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validateAccess(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

        VideoGameDeveloperDTO developer = developerService.getActiveById(id);
        if (developer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Developer no encontrado o inactivo"));
        }

        return ResponseEntity.ok(developer);
    }

}

