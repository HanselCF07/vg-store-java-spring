package com.example.vg_store.repository;
import com.example.vg_store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserPublicKey(String userPublicKey);
}
