package com.corner.user.service.attendance;

import com.corner.user.dto.AttendanceRequest;
import com.corner.user.entity.Attendance;
import com.corner.user.entity.StudentEntity;

import java.util.List;
import java.util.UUID;

public interface AttendanceService{
    Attendance toggleAttendance(AttendanceRequest request);

    List<Attendance> getAllAttendance();

    List<Attendance> toggleAttendanceForMultipleStudents(List<AttendanceRequest> request);

    Attendance getAttendanceById(UUID attendanceId);

    Attendance updateAttendance(UUID id, AttendanceRequest request);

    List<Attendance> getAttendanceByStudentName(String username);
}
