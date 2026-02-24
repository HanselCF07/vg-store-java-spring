package com.example.vg_store.dto;

import java.time.LocalDateTime;

public class VideoGameDeveloperDTO {
    private Integer id;
    private String name;
    private String country;
    private String description;
    private LocalDateTime createdAt;
    private Integer recordStatus;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Integer getRecordStatus() { return recordStatus; }
    public void setRecordStatus(Integer recordStatus) { this.recordStatus = recordStatus; }
}

