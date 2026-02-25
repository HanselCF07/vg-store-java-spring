package com.example.vg_store.mapper;

import com.example.vg_store.dto.VideoGameDTO;
import com.example.vg_store.model.VideoGame;
import com.example.vg_store.model.VideoGameDeveloper;

public class VideoGameMapper {

    public static VideoGameDTO toDTO(VideoGame entity) {
        VideoGameDTO dto = new VideoGameDTO();
        dto.setPublicId(entity.getPublicId());
        dto.setStatus(entity.getStatus());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setDeveloperId(entity.getDeveloper() != null ? entity.getDeveloper().getId() : null);
        dto.setVideoGameDeveloper(entity.getDeveloper());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRecordStatus(entity.getRecordStatus());
        return dto;
    }

    public static VideoGame toEntity(VideoGameDTO dto, VideoGameDeveloper developer) {
        VideoGame entity = new VideoGame();
        entity.setPublicId(dto.getPublicId());
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setDeveloper(developer); // se pasa el objeto developer desde el servicio
        entity.setReleaseDate(dto.getReleaseDate());
        //entity.setCreatedAt(dto.getCreatedAt());
        //entity.setRecordStatus(dto.getRecordStatus());
        return entity;
    }

    public static void updateEntity(VideoGame entity, VideoGameDTO dto, VideoGameDeveloper developer) {
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setDeveloper(developer);
        entity.setReleaseDate(dto.getReleaseDate());
        // entity.setRecordStatus(dto.getRecordStatus());
        // createdAt normalmente no se actualiza
    }

}

