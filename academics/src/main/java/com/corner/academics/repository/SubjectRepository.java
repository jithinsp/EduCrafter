package com.corner.academics.repository;

import com.corner.academics.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subjects,UUID> {
}
