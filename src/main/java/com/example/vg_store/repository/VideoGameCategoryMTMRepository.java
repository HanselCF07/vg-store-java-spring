package com.example.vg_store.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vg_store.model.VideoGameCategoryMTM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface VideoGameCategoryMTMRepository extends JpaRepository<VideoGameCategoryMTM, Integer> {
    Page<VideoGameCategoryMTM> findByCategory_IdInAndRecordStatus(List<Integer> categoryIds, Integer recordStatus, Pageable pageable);
}