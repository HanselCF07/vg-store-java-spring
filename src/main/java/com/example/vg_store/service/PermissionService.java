package com.example.vg_store.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.vg_store.repository.UserRepository;
import com.example.vg_store.repository.RolePermissionRepository;
import com.example.vg_store.model.User;
import com.example.vg_store.model.Role;
import com.example.vg_store.model.RolePermission;
import java.util.List;
import java.util.Map;


@Service
public class PermissionService {
    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public PermissionService(UserRepository userRepository,
                             RolePermissionRepository rolePermissionRepository) {
        this.userRepository = userRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public boolean tienePermiso(String userPublicKey, List<String> requiredPermissions) {
        User user = userRepository.findByUserPublicKey(userPublicKey);
        if (user == null || user.getRecordStatus() != 1 || user.getUserStatus() == null || user.getUserStatus().getStatusId() != 1) {
            return false;
        }

        Role role = user.getRole();
        if (role == null || role.getRecordStatus() != 1) {
            return false;
        }

        List<RolePermission> rolePermissions = rolePermissionRepository.findByRole_RoleIdAndRecordStatus(role.getRoleId(), 1);

        return rolePermissions.stream()
                .map(RolePermission::getPermission)
                .anyMatch(requiredPermissions::contains);
    }

    // Método centralizado para controladores
    public ResponseEntity<?> validarAcceso(String userPublicKey, List<String> requiredPermissions) {
        if (!tienePermiso(userPublicKey, requiredPermissions)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Invalid access"));
        }
        return null; // acceso válido
    }

}

