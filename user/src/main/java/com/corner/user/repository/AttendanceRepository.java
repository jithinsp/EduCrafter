package com.corner.user.repository;

import com.corner.user.entity.Attendance;
import com.corner.user.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
//    Attendance findByStudentId(UUID studentId);

    List<Attendance> findByStudent(StudentEntity student);
}
