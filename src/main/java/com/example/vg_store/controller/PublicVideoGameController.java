package com.example.vg_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.example.vg_store.service.VideoGameService;
import com.example.vg_store.dto.VideoGameDTO;

import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/vg-hc-store/data/public/video-games")
public class PublicVideoGameController {

    private final VideoGameService videoGameService;

    public PublicVideoGameController(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Integer developerId) {

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validarAcceso(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

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
    public ResponseEntity<?> getByPublicId(@PathVariable String publicId) {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Basic Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validarAcceso(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

        VideoGameDTO videoGame = videoGameService.getByPublicId(publicId);
        if (videoGame == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Videojuego no encontrado o inactivo"));
        }

        return ResponseEntity.ok(videoGame);
    }

}

