package com.corner.user.repository;

import com.corner.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCredentialRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
