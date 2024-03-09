package com.corner.academics.repository;

import com.corner.academics.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, UUID> {
}
