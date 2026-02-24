package com.example.vg_store.repository;

import com.example.vg_store.model.VideoGameDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VideoGameDeveloperRepository extends JpaRepository<VideoGameDeveloper, Integer> {
    VideoGameDeveloper findByName(String name);

    List<VideoGameDeveloper> findByRecordStatus(Integer recordStatus);

    Optional<VideoGameDeveloper> findByIdAndRecordStatus(Integer id, Integer recordStatus);
}