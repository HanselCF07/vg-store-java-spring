package com.example.vg_store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.vg_store.service.PermissionService;
import com.example.vg_store.service.VideoGameService;
import com.example.vg_store.dto.VideoGameDTO;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/vg-hc-store/data/video-games")
public class VideoGameController {

    private final VideoGameService videoGameService;
    private final PermissionService permissionService;

    public VideoGameController(VideoGameService videoGameService, PermissionService permissionService) {
        this.videoGameService = videoGameService;
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VideoGameDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> invalidAccess = permissionService.validateAccess(userPublicKey, list_permission);
        if (invalidAccess != null) {
            return invalidAccess;
        }

        VideoGameDTO nuevo = videoGameService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{publicId}")
    public ResponseEntity<?> update(@PathVariable String publicId, @RequestBody VideoGameDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> invalidAccess = permissionService.validateAccess(userPublicKey, list_permission);
        if (invalidAccess != null) {
            return invalidAccess;
        }

        VideoGameDTO actualizado = videoGameService.update(publicId, dto);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado"));
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<?> delete(@PathVariable String publicId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> invalidAccess = permissionService.validateAccess(userPublicKey, list_permission);
        if (invalidAccess != null) {
            return invalidAccess;
        }

        boolean eliminado = videoGameService.delete(publicId);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado"));
        }

        return ResponseEntity.noContent().build();
    }

}

