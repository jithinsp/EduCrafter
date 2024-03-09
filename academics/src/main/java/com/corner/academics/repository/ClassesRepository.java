package com.corner.academics.repository;

import com.corner.academics.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassesRepository extends JpaRepository<Classes,UUID> {
}
