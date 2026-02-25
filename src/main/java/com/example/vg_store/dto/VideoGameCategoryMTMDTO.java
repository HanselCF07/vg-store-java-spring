package com.example.vg_store.dto;

import com.example.vg_store.model.VideoGame;
import com.example.vg_store.model.VideoGameCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;


public class VideoGameCategoryMTMDTO {
    private Integer id;
    @JsonIgnore
    private Integer videoGameId;
    private VideoGame videoGame;
    @JsonIgnore
    private Integer categoryId;
    private VideoGameCategory videoGameCategory;
    private LocalDateTime createdAt;
    private Integer recordStatus;

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getVideoGameId() {
        return videoGameId;
    }
    public void setVideoGameId(Integer videoGameId) {
        this.videoGameId = videoGameId;
    }
    public VideoGame getVideoGame() {
        return videoGame;
    }
    public void setVideoGame(VideoGame videoGame) {
        this.videoGame = videoGame;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public VideoGameCategory getVideoGameCategory() {
        return videoGameCategory;
    }
    public void setVideoGameCategory(VideoGameCategory videoGameCategory) {
        this.videoGameCategory = videoGameCategory;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Integer getRecordStatus() {
        return recordStatus;
    }
    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
}

