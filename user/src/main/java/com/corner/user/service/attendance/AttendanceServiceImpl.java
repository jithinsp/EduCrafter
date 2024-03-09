package com.corner.user.service.attendance;

import com.corner.user.dto.AttendanceRequest;
import com.corner.user.entity.Attendance;
import com.corner.user.entity.StudentEntity;
import com.corner.user.repository.AttendanceRepository;
import com.corner.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    StudentRepository studentInfoRepository;

    public Attendance toggleAttendance(AttendanceRequest request) {
        Attendance attendance = new Attendance();
        StudentEntity student = studentInfoRepository
                .findById(request.getUserId())//temporary change get
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + request.getUserId() + " not found"));
//        attendance.setStudentInfo(student);
        attendance.setDate(LocalDateTime.now());
        attendance.setAttended(request.getIsPresent());

//        List<Attendance> attendanceList = new ArrayList<>();
//        attendanceList.add(attendance);
//        student.setAttendances(attendanceList);
//        studentInfoRepository.save(student);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> toggleAttendanceForMultipleStudents(List<AttendanceRequest> requestList) {
        List<Attendance> attendanceList = new ArrayList<>();
        for (AttendanceRequest request : requestList) {
            Attendance attendance = new Attendance();
            StudentEntity student = studentInfoRepository
                    .findById(request.getUserId())
                    .orElseThrow(()->new EntityNotFoundException("student not found"));
//            attendance.setStudentId(request.getUserId());
            attendance.setStudent(student);
            attendance.setAttended(request.getIsPresent());
            attendance.setDate(LocalDateTime.now());
            attendanceList.add(attendance);
        }
        return attendanceRepository.saveAll(attendanceList);
    }

    public Attendance getAttendanceById(UUID attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(()->new EntityNotFoundException("Not found"));
    }

    public Attendance updateAttendance(UUID id, AttendanceRequest request) {
        Attendance updatedAttendance = attendanceRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Not found"));
        updatedAttendance.setAttended(request.getIsPresent());
        return attendanceRepository.save(updatedAttendance);
    }

    public List<Attendance> getAttendanceByStudentName(String username) {
        StudentEntity student = studentInfoRepository.findByEmail(username)
                .orElseThrow(()->new EntityNotFoundException("Student not found"));
        return attendanceRepository.findByStudent(student);
    }
}
