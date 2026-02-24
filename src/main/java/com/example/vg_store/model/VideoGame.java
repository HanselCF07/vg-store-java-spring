package com.example.vg_store.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
//import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "video_game", schema = "vg_store")
public class VideoGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL → autoincrement
    @Column(name = "video_game_id")
    private Integer id;

    @Column(name = "video_game_public_id", unique = true)
    private String publicId;

    @Column(nullable = false)
    private Integer status = 1;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Relación con la tabla video_game_developer
    @ManyToOne
    @JoinColumn(name = "video_game_developer_id", referencedColumnName = "video_game_developer_id")
    private VideoGameDeveloper developer;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "record_status", nullable = false)
    private Integer recordStatus = 1;


    /*@PostPersist
    public void generatePublicId() {
        if (this.publicId == null) {
            String basePart = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
            this.publicId = basePart + this.id;
        }
    }*/


    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPublicId() { return publicId; }
    public void setPublicId(String publicId) { this.publicId = publicId; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public VideoGameDeveloper getDeveloper() { return developer; }
    public void setDeveloper(VideoGameDeveloper developer) { this.developer = developer; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Integer getRecordStatus() { return recordStatus; }
    public void setRecordStatus(Integer recordStatus) { this.recordStatus = recordStatus; }
}