package com.example.vg_store.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "video_game_developer", schema = "vg_store")
public class VideoGameDeveloper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL → autoincrement
    @Column(name = "video_game_developer_id")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String country;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "record_status")
    private Integer recordStatus = 1;

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

    // 
    @OneToMany(mappedBy = "developer")
    private List<VideoGame> videoGames;
}

