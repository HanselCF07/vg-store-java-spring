package com.example.vg_store.mapper;

import com.example.vg_store.model.VideoGameCategoryMTM;
import com.example.vg_store.model.VideoGame;
import com.example.vg_store.model.VideoGameCategory;
import com.example.vg_store.dto.VideoGameCategoryMTMDTO;


public class VideoGameCategoryMTMMapper {

    public static VideoGameCategoryMTMDTO toDTO(VideoGameCategoryMTM entity) {
        VideoGameCategoryMTMDTO dto = new VideoGameCategoryMTMDTO();
        dto.setId(entity.getId());

        // IDs
        dto.setVideoGameId(entity.getVideoGame().getId());
        dto.setCategoryId(entity.getCategory().getId());

        // Objetos completos
        dto.setVideoGame(entity.getVideoGame());
        dto.setVideoGameCategory(entity.getCategory());

        // Objetos completos convertidos a DTO
        //dto.setVideoGameDTO(VideoGameMapper.toDTO(entity.getVideoGame()));
        //dto.setVideoGameCategoryDTO(VideoGameCategoryMapper.toDTO(entity.getCategory()));
        
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRecordStatus(entity.getRecordStatus());
        return dto;
    }

    public static VideoGameCategoryMTM toEntity(VideoGameCategoryMTMDTO dto, VideoGame videoGame, VideoGameCategory category) {
        VideoGameCategoryMTM entity = new VideoGameCategoryMTM();
        //entity.setId(dto.getId());
        entity.setVideoGame(videoGame);
        entity.setCategory(category);
        //entity.setCreatedAt(dto.getCreatedAt());
        //entity.setRecordStatus(dto.getRecordStatus());
        return entity;
    }

    public static void updateEntity(VideoGameCategoryMTM entity, VideoGameCategoryMTMDTO dto, VideoGame videoGame, VideoGameCategory category) {
        entity.setVideoGame(videoGame);
        entity.setCategory(category);
        //entity.setRecordStatus(dto.getRecordStatus());
    }

}


