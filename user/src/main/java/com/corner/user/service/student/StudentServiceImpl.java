package com.corner.user.service.student;

import com.corner.user.client.AcademicsService;
import com.corner.user.client.LoginClient;
import com.corner.user.dto.LoginDetails;
import com.corner.user.dto.RegisterStudentInAcademics;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.entity.Role;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.repository.StudentRepository;
import com.corner.user.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginClient loginClient;
    @Autowired
    AcademicsService academicsService;
    @Autowired
    JwtUtil jwtUtil;

    public StudentEntity addStudent(RegisterStudentRequest registerStudentRequest) {
        if(studentRepository.existsByEmail(registerStudentRequest.getEmail())){
//            throw new RuntimeException("Try with a different mail id");
            throw new DataIntegrityViolationException("Duplicate Mail id. Try with a different one");
        }
        StudentEntity newStudent = new StudentEntity();
        BeanUtils.copyProperties(registerStudentRequest, newStudent); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerStudentRequest.getPassword());
        newStudent.setPassword(hashedPassword);
        newStudent.setRole(Role.STUDENT);
        StudentEntity createdUser = studentRepository.save(newStudent);
        newStudent.setId(createdUser.getId());


        LoginDetails loginDetails = new LoginDetails(newStudent.getId(),newStudent.getEmail(),
                newStudent.getName(),registerStudentRequest.getPassword(),newStudent.getRole());

        loginClient.signupUser(loginDetails);
        RegisterStudentInAcademics registerStudentInAcademics = new RegisterStudentInAcademics(
                newStudent.getId(),newStudent.getName(),"10A");
        academicsService.signupUser(registerStudentInAcademics);


        System.out.println("successfully registered");
        return newStudent;
    }

    public List<StudentEntity> getAllByRole(Role role) {
        List<StudentEntity> users = studentRepository.findByRole(role);
        return users;
        }

    public String extractUsername(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = null;
        if (header.startsWith("Bearer ")) {
            token = header.substring(7);
        }
        String username =jwtUtil.extractUsername(token);
        return username;
    }

    public StudentEntity getUserByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException(
                "Not found"));
    }
    }