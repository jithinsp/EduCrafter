package com.corner.user.controller;

import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.service.student.StudentService;
import com.corner.user.service.user.UserCredentialService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/profile")
    public ResponseEntity<StudentEntity> getUserProfile(HttpServletRequest request){
        String username = studentService.extractUsername(request);
        StudentEntity users = studentService.getUserByEmail(username);
        System.out.println(users);
        return ResponseEntity.ok(users);
    }
}