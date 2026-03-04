package com.example.vg_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.vg_store.service.VideoGameCategoryMTMService;
import com.example.vg_store.dto.VideoGameCategoryMTMDTO;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/vg-hc-store/data/public/video-game-category-mtm")
public class PublicVideoGameCategoryMTMController {

    private final VideoGameCategoryMTMService service;

    public PublicVideoGameCategoryMTMController(VideoGameCategoryMTMService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getByCategories(
            @RequestParam List<Integer> categoryIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String userPublicKey = (String) auth.getPrincipal();

        // List<String> list_permission = List.of("Full Access", "Manager");

        // ResponseEntity<?> invalidAccess = permissionService.validateAccess(userPublicKey, list_permission);
        // if (invalidAccess != null) {
        //     return invalidAccess;
        // }

        Pageable pageable = PageRequest.of(page, size);
        
        Page<VideoGameCategoryMTMDTO> dataResult;
        dataResult = service.getByCategories(categoryIds, pageable);

        return ResponseEntity.ok(dataResult);
    }

}

