package com.example.vg_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.vg_store.service.VideoGameCategoryService;
import com.example.vg_store.dto.VideoGameCategoryDTO;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/vg-hc-store/data/public/video-game-categories")
public class PublicVideoGameCategoryController {

    private final VideoGameCategoryService videoGameCategoryService;

    public PublicVideoGameCategoryController(VideoGameCategoryService videoGameCategoryService) {
        this.videoGameCategoryService = videoGameCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validarAcceso(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

        return ResponseEntity.ok(videoGameCategoryService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validarAcceso(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

        VideoGameCategoryDTO videoGameCategory = videoGameCategoryService.getActiveById(id);
        if (videoGameCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Categoria no encontrada o inactiva"));
        }

        return ResponseEntity.ok(videoGameCategory);
    }

}

