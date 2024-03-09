package com.corner.user.service.user;

import com.corner.user.client.LoginClient;
import com.corner.user.dto.LoginDetails;
import com.corner.user.dto.RegisterParentRequest;
import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.dto.RegisterTeacherRequest;
import com.corner.user.entity.Role;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.repository.StudentRepository;
import com.corner.user.repository.UserCredentialRepository;
import com.corner.user.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
    @Autowired
    UserCredentialRepository userCredentialRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginClient loginClient;
    @Autowired
    JwtUtil jwtUtil;

    public UserEntity addAdmin(RegisterStudentRequest registerStudentRequest) {
        if(userCredentialRepository.existsByEmail(registerStudentRequest.getEmail())){
//            throw new RuntimeException("Try with a different mail id");
            throw new DataIntegrityViolationException("Duplicate Mail id. Try with a different one");
        }
        UserEntity users = new UserEntity();
        BeanUtils.copyProperties(registerStudentRequest, users); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerStudentRequest.getPassword());
        users.setPassword(hashedPassword);
        users.setRole(Role.ADMIN);
        UserEntity createdUser = userCredentialRepository.save(users);
        users.setId(createdUser.getId());
        LoginDetails loginDetails = new LoginDetails(users.getId(),users.getEmail(),
                users.getName(),registerStudentRequest.getPassword(),Role.ADMIN);
        loginClient.signupUser(loginDetails);
        return users;
    }

    public UserEntity addTeacher(RegisterTeacherRequest registerTeacherRequest) {
        if(userCredentialRepository.existsByEmail(registerTeacherRequest.getEmail())){
//            throw new RuntimeException("Try with a different mail id");
            throw new DataIntegrityViolationException("Duplicate Mail id. Try with a different one");
        }
        UserEntity newTeacher = new UserEntity();
        BeanUtils.copyProperties(registerTeacherRequest, newTeacher); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerTeacherRequest.getPassword());
        newTeacher.setPassword(hashedPassword);
        newTeacher.setRole(Role.TEACHER);
        UserEntity createdUser = userCredentialRepository.save(newTeacher);
        newTeacher.setId(createdUser.getId());
        LoginDetails loginDetails = new LoginDetails(newTeacher.getId(),newTeacher.getEmail(),
                newTeacher.getName(),registerTeacherRequest.getPassword(),Role.TEACHER);
        loginClient.signupUser(loginDetails);
        return newTeacher;
    }

    public List<UserEntity> getAllByRole(Role role) {
        List<UserEntity> users = userCredentialRepository.findByRole(role);
        return users;
    }

    public UserEntity addParent(RegisterParentRequest registerParentRequest) {
        if(userCredentialRepository.existsByEmail(registerParentRequest.getEmail())){
//            throw new RuntimeException("Try with a different mail id");
            throw new DataIntegrityViolationException("Duplicate Mail id. Try with a different one");
        }
        UserEntity newParent = new UserEntity();
        BeanUtils.copyProperties(registerParentRequest, newParent); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerParentRequest.getPassword());
        newParent.setPassword(hashedPassword);
        newParent.setRole(Role.PARENT);
        UserEntity createdUser = userCredentialRepository.save(newParent);
        newParent.setId(createdUser.getId());
        StudentEntity student =
                studentRepository.findById(registerParentRequest.getStudentId())
                        .orElseThrow(() -> new EntityNotFoundException("Student with ID " + registerParentRequest.getStudentId() + " not found"));
        student.setParent(newParent);
        studentRepository.save(student);
        LoginDetails loginDetails = new LoginDetails(newParent.getId(),newParent.getEmail(),
                newParent.getName(),registerParentRequest.getPassword(),Role.PARENT);
        loginClient.signupUser(loginDetails);
        return newParent;
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

    public UserEntity getUserByEmail(String email) {
        return userCredentialRepository.findByEmail(email);
    }

    public UserEntity getById(UUID userId) {
        return userCredentialRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId
                        + " not found"));
    }
}