package com.example.vg_store.mapper;

import com.example.vg_store.dto.VideoGameCategoryDTO;
import com.example.vg_store.model.VideoGameCategory;

public class VideoGameCategoryMapper {

    public static VideoGameCategoryDTO toDTO(VideoGameCategory entity) {
        VideoGameCategoryDTO dto = new VideoGameCategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRecordStatus(entity.getRecordStatus());
        return dto;
    }

    public static VideoGameCategory toEntity(VideoGameCategoryDTO dto) {
        VideoGameCategory entity = new VideoGameCategory();
        //entity.setId(dto.getId());
        entity.setName(dto.getName());
        // entity.setCreatedAt(dto.getCreatedAt());
        // entity.setRecordStatus(dto.getRecordStatus());
        return entity;
    }

    public static void updateEntity(VideoGameCategory entity, VideoGameCategoryDTO dto) {
        entity.setName(dto.getName());
        //entity.setRecordStatus(dto.getRecordStatus());
    }

}

