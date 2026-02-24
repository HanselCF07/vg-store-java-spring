package com.example.vg_store.repository;
import com.example.vg_store.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
    List<RolePermission> findByRole_RoleIdAndRecordStatus(Integer roleId, Integer recordStatus);
}

