package com.corner.user.service.student;

import com.corner.user.client.LoginClient;
import com.corner.user.dto.LoginDetails;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.entity.Role;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginClient loginClient;

    public StudentEntity addStudent(RegisterStudentRequest registerStudentRequest) {
        if(studentRepository.existsByEmail(registerStudentRequest.getEmail())){
            throw new RuntimeException("Try with a different mail id");
        }
        StudentEntity newStudent = new StudentEntity();
        BeanUtils.copyProperties(registerStudentRequest, newStudent); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerStudentRequest.getPassword());
        newStudent.setPassword(hashedPassword);
        newStudent.setRole(Role.STUDENT);
        StudentEntity createdUser = studentRepository.save(newStudent);
        newStudent.setId(createdUser.getId());
        LoginDetails loginDetails = new LoginDetails(newStudent.getId(),newStudent.getEmail(),
                newStudent.getName(),newStudent.getPassword(),newStudent.getRole());
        loginClient.signupUser(loginDetails);
        System.out.println("successfully registered");
        return newStudent;
    }
}
