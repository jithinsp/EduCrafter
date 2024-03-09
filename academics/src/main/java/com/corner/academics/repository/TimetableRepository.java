package com.corner.academics.repository;

import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimetableRepository extends JpaRepository<Timetable, UUID> {

    Timetable findByClasses(Classes classes);
}
