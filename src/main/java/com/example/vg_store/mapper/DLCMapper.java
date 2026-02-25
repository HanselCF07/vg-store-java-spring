package com.example.vg_store.mapper;

import com.example.vg_store.model.DLC;
import com.example.vg_store.model.VideoGame;
import com.example.vg_store.dto.DLCDTO;


public class DLCMapper {

    public static DLCDTO toDTO(DLC entity) {
        DLCDTO dto = new DLCDTO();
        dto.setPublicId(entity.getPublicId());
        dto.setVideoGameId(entity.getVideoGame().getId());
        dto.setStatus(entity.getStatus());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRecordStatus(entity.getRecordStatus());
        return dto;
    }

    public static DLC toEntity(DLCDTO dto, VideoGame videoGame) {
        DLC entity = new DLC();
        entity.setPublicId(dto.getPublicId());
        entity.setVideoGame(videoGame);
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setRecordStatus(dto.getRecordStatus());
        return entity;
    }

    public static void updateEntity(DLC entity, DLCDTO dto, VideoGame videoGame) {
        entity.setVideoGame(videoGame);
        entity.setStatus(dto.getStatus());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setRecordStatus(dto.getRecordStatus());
    }

}

