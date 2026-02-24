package com.example.vg_store.mapper;

import com.example.vg_store.dto.VideoGameDeveloperDTO;
import com.example.vg_store.model.VideoGameDeveloper;

public class VideoGameDeveloperMapper {

    public static VideoGameDeveloperDTO toDTO(VideoGameDeveloper entity) {
        VideoGameDeveloperDTO dto = new VideoGameDeveloperDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountry(entity.getCountry());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRecordStatus(entity.getRecordStatus());
        return dto;
    }

    public static VideoGameDeveloper toEntity(VideoGameDeveloperDTO dto) {
        VideoGameDeveloper entity = new VideoGameDeveloper();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setDescription(dto.getDescription());
        //entity.setCreatedAt(dto.getCreatedAt());
        //entity.setRecordStatus(dto.getRecordStatus());
        return entity;
    }

    public static void updateEntity(VideoGameDeveloper entity, VideoGameDeveloperDTO dto) {
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setDescription(dto.getDescription());
        entity.setRecordStatus(dto.getRecordStatus());
        // createdAt normalmente no se actualiza
    }
}