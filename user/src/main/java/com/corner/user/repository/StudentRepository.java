package com.corner.user.repository;

import com.corner.user.entity.Role;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    boolean existsByEmail(String email);

    List<StudentEntity> findByRole(Role role);

    StudentEntity findByEmail(String email);
}
