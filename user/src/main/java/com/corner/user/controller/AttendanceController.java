package com.corner.user.controller;


import com.corner.user.dto.AttendanceRequest;
import com.corner.user.entity.Attendance;
import com.corner.user.service.attendance.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("user/attendance/")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllAttendance(){
        List<Attendance> attendanceList =  attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendanceList);
    }

    @GetMapping("get/{attendanceId}")
    public ResponseEntity<?> getAttendanceById(@PathVariable UUID attendanceId){
        Attendance attendance =  attendanceService.getAttendanceById(attendanceId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("getByStudent/{username}")
    public ResponseEntity<?> getByStudent(@PathVariable String username){
        List<Attendance> attendance =  attendanceService.getAttendanceByStudentName(username);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping("mark")
    public ResponseEntity<?> toggleAttendance(@RequestBody List<AttendanceRequest> request){
        System.out.println(request);
        try {
//            Attendance attendance = attendanceService.toggleAttendance(request);
            List<Attendance> attendanceList = attendanceService.toggleAttendanceForMultipleStudents(request);
            return ResponseEntity.status(HttpStatus.OK).body(attendanceList);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("editAttendance/{id}")
    public ResponseEntity<?> editAttendance(@PathVariable UUID id,
                                            @RequestBody AttendanceRequest request){
        try {
            Attendance attendance = attendanceService.updateAttendance(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(attendance);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}