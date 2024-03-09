package com.corner.academics.controller;


import com.corner.academics.dto.RegisterStudentRequest;
import com.corner.academics.entity.StudentInfo;
import com.corner.academics.service.student.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academics/student/")
public class StudentInfoController {
    @Autowired
    StudentInfoService studentInfoService;

    @GetMapping("allStudents")
    public ResponseEntity<List<StudentInfo>> getStudents(){
        System.out.println("allStudents");
        List<StudentInfo> studentInfoList = studentInfoService.getAllStudents();
        return ResponseEntity.ok(studentInfoList);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signupUser(@RequestBody RegisterStudentRequest signupRequest){
        System.out.println(signupRequest);
        StudentInfo user = studentInfoService.createUser(signupRequest);

        if (user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }
}
