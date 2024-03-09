package com.corner.user.controller;

import com.corner.user.dto.RegisterParentRequest;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.dto.RegisterTeacherRequest;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.service.student.StudentService;
import com.corner.user.service.user.UserCredentialService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            StudentEntity student = studentService.addStudent(registerStudentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create" +
                    " student: "+ e.getMessage());
        }
    }
    @PostMapping("/parent")
    public ResponseEntity<?> registerParent(@RequestBody RegisterParentRequest registerParentRequest){
        try {
            UserEntity parent = userCredentialService.addParent(registerParentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(parent);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create" +
                    " student: "+ e.getMessage());
        }
    }
    @PostMapping("/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody RegisterTeacherRequest registerTeacherRequest){
        try {
            UserEntity teacher = userCredentialService.addTeacher(registerTeacherRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create" +
                    " student: "+ e.getMessage());
        }
//        UserEntity teacher = userCredentialService.addTeacher(registerTeacherRequest);
//        if (teacher != null){
//            return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create teacher");
//        }
    }
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterStudentRequest registerStudentRequest){
        try {
            UserEntity admin = userCredentialService.addAdmin(registerStudentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(admin);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create" +
                    " student: "+ e.getMessage());
        }
    }
}