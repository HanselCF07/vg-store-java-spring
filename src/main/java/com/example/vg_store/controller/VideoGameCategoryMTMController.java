package com.example.vg_store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import com.example.vg_store.service.PermissionService;
import com.example.vg_store.service.VideoGameCategoryMTMService;
import com.example.vg_store.dto.VideoGameCategoryMTMDTO;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/vg-hc-store/data/video-game-category-mtm")
public class VideoGameCategoryMTMController {

    private final VideoGameCategoryMTMService service;
    private final PermissionService permissionService;

    public VideoGameCategoryMTMController(VideoGameCategoryMTMService service, PermissionService permissionService) {
        this.service = service;
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<?> getByCategories(
            @RequestParam List<Integer> categoryIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }
        Pageable pageable = PageRequest.of(page, size);
        
        Page<VideoGameCategoryMTMDTO> dataResult;
        dataResult = service.getByCategories(categoryIds, pageable);

        return ResponseEntity.ok(dataResult);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody VideoGameCategoryMTMDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameCategoryMTMDTO nuevo = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Integer id, @RequestBody VideoGameCategoryMTMDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPublicKey = (String) auth.getPrincipal();

        List<String> list_permission = List.of("Full Access", "Manager");

        ResponseEntity<?> accesoInvalido = permissionService.validarAcceso(userPublicKey, list_permission);
        if (accesoInvalido != null) {
            return accesoInvalido;
        }

        VideoGameCategoryMTMDTO actualizado = service.update(id, dto);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Registro no encontrado"));
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

        boolean eliminado = service.delete(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado"));
        }

        return ResponseEntity.noContent().build();
    }

}