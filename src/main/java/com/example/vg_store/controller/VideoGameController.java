package com.example.vg_store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.example.vg_store.service.PermissionService;
import com.example.vg_store.service.VideoGameService;
import com.example.vg_store.dto.VideoGameDTO;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/vg-hc-store/data/protected/video-games")
public class VideoGameController {

    private final VideoGameService videoGameService;
    private final PermissionService permissionService;

    public VideoGameController(VideoGameService videoGameService, PermissionService permissionService) {
        this.videoGameService = videoGameService;
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Integer developerId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<VideoGameDTO> dataResult;
        if (developerId != null) {
            dataResult = videoGameService.getActiveVideoGamesByDeveloper(developerId, pageable);
        } else {
            dataResult = videoGameService.getActiveVideoGames(pageable);
        }

        return ResponseEntity.ok(dataResult);
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<?> obtenerPorPublicId(@PathVariable String publicId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameDTO videoGame = videoGameService.getByPublicId(publicId);
        if (videoGame == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado o inactivo"));
        }

        return ResponseEntity.ok(videoGame);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody VideoGameDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameDTO nuevo = videoGameService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{publicId}")
    public ResponseEntity<?> editar(@PathVariable String publicId, @RequestBody VideoGameDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameDTO actualizado = videoGameService.update(publicId, dto);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado"));
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<?> eliminar(@PathVariable String publicId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        boolean eliminado = videoGameService.delete(publicId);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado"));
        }

        return ResponseEntity.noContent().build();
    }
}

