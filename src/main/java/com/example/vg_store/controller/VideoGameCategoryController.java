package com.example.vg_store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
/*import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;*/
import org.springframework.http.HttpStatus;
import com.example.vg_store.service.PermissionService;
import com.example.vg_store.service.VideoGameCategoryService;
import com.example.vg_store.dto.VideoGameCategoryDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/vg-hc-store/data/video-game-categories")
public class VideoGameCategoryController {

    private final VideoGameCategoryService videoGameCategoryService;
    private final PermissionService permissionService;

    public VideoGameCategoryController(VideoGameCategoryService videoGameCategoryService, PermissionService permissionService) {
        this.videoGameCategoryService = videoGameCategoryService;
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        return ResponseEntity.ok(videoGameCategoryService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameCategoryDTO videoGameCategory = videoGameCategoryService.getActiveById(id);
        if (videoGameCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Categoria no encontrada o inactiva"));
        }

        return ResponseEntity.ok(videoGameCategory);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody VideoGameCategoryDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameCategoryDTO nuevo = videoGameCategoryService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VideoGameCategoryDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameCategoryDTO actualizado = videoGameCategoryService.update(id, dto);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado"));
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        boolean eliminado = videoGameCategoryService.delete(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Categoria no encontrada"));
        }

        return ResponseEntity.noContent().build();
    }


}
