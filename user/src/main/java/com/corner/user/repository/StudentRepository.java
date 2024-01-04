package com.corner.user.repository;

import com.corner.user.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    boolean existsByEmail(String email);
}
