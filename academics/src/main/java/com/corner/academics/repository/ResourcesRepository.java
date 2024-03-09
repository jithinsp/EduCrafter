package com.corner.academics.repository;

import com.corner.academics.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourcesRepository extends JpaRepository<Resources, UUID> {
}
