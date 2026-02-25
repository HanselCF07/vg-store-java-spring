package com.example.vg_store.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
//import java.util.UUID;
//import java.time.LocalDate;


@Entity
@Table(name = "video_game_category_mtm", schema = "vg_store")
public class VideoGameCategoryMTM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_game_category_mtm_id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "video_game_id", referencedColumnName = "video_game_id")
    private VideoGame videoGame;

    @ManyToOne()
    @JoinColumn(name = "video_game_category_id", referencedColumnName = "video_game_category_id")
    private VideoGameCategory category;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "record_status", nullable = false)
    private Integer recordStatus = 1;


    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VideoGame getVideoGame() {
        return videoGame;
    }

    public void setVideoGame(VideoGame videoGame) {
        this.videoGame = videoGame;
    }

    public VideoGameCategory getCategory() {
        return category;
    }

    public void setCategory(VideoGameCategory category) {
        this.category = category;
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