package com.example.vg_store.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vg_store.model.DLC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DLCRepository extends JpaRepository<DLC, Integer> {
    Page<DLC> findByRecordStatus(Integer recordStatus, Pageable pageable);
}
