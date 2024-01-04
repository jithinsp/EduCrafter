package com.corner.app.repository;

import com.corner.app.entity.Role;
import com.corner.app.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredential,Long> {
    boolean existsByEmail(String email);
    Optional<UserCredential> findByEmail(String email);
    boolean existsByRole(Role role);
}
