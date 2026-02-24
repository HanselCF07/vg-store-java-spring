package com.example.vg_store.repository;
import com.example.vg_store.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VideoGameRepository extends JpaRepository<VideoGame, Integer> {
    VideoGame findByPublicId(String publicId);

    Page<VideoGame> findByRecordStatus(Integer recordStatus, Pageable pageable);

    Page<VideoGame> findByRecordStatusAndDeveloper_Id(Integer recordStatus, Integer developerId, Pageable pageable);
}