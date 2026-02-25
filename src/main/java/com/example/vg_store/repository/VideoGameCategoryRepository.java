package com.example.vg_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vg_store.model.VideoGameCategory;
import java.util.List;
import java.util.Optional;


public interface VideoGameCategoryRepository extends JpaRepository<VideoGameCategory, Integer> {
    List<VideoGameCategory> findByRecordStatus(Integer recordStatus);
    Optional<VideoGameCategory> findByIdAndRecordStatus(Integer id, Integer recordStatus);
}







