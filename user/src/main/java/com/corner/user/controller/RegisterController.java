package com.corner.user.controller;

import com.corner.user.dto.RegisterParentRequest;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.dto.RegisterTeacherRequest;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.service.student.StudentService;
import com.corner.user.service.user.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/register")
public class RegisterController {
    @Autowired
    UserCredentialService userCredentialService;
    @Autowired
    StudentService studentService;
    @PostMapping("/student")
    public ResponseEntity<?> registerStudent(@RequestBody RegisterStudentRequest registerStudentRequest){
        System.out.println("registering student");
        StudentEntity student = studentService.addStudent(registerStudentRequest);
        if (student != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create teacher");
        }
    }
    @PostMapping("/parent")
    public ResponseEntity<?> registerParent(@RequestBody RegisterParentRequest registerParentRequest){
        UserEntity parent = userCredentialService.addParent(registerParentRequest);
        if (parent != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(parent);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create teacher");
        }
    }
    @PostMapping("/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody RegisterTeacherRequest registerTeacherRequest){
        UserEntity teacher = userCredentialService.addTeacher(registerTeacherRequest);
        if (teacher != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create teacher");
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterStudentRequest registerStudentRequest){
        UserEntity admin = userCredentialService.addAdmin(registerStudentRequest);
        if (admin != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(admin);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create admin");
        }
    }
}