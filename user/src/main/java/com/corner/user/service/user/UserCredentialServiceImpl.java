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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public UserEntity addAdmin(RegisterStudentRequest registerStudentRequest) {
        if(userCredentialRepository.existsByEmail(registerStudentRequest.getEmail())){
            throw new RuntimeException("Try with a different mail id");
        }
        UserEntity users = new UserEntity();
        BeanUtils.copyProperties(registerStudentRequest, users); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerStudentRequest.getPassword());
        users.setPassword(hashedPassword);
        users.setRole(Role.ADMIN);
        UserEntity createdUser = userCredentialRepository.save(users);
        users.setId(createdUser.getId());
        LoginDetails loginDetails = new LoginDetails(users.getId(),users.getEmail(),
                users.getName(),users.getPassword(),Role.ADMIN);
        loginClient.signupUser(loginDetails);
        return users;
    }



    public UserEntity addTeacher(RegisterTeacherRequest registerTeacherRequest) {
        if(userCredentialRepository.existsByEmail(registerTeacherRequest.getEmail())){
            throw new RuntimeException("Try with a different mail id");
        }
        UserEntity newTeacher = new UserEntity();
        BeanUtils.copyProperties(registerTeacherRequest, newTeacher); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerTeacherRequest.getPassword());
        newTeacher.setPassword(hashedPassword);
        newTeacher.setRole(Role.TEACHER);
        UserEntity createdUser = userCredentialRepository.save(newTeacher);
        newTeacher.setId(createdUser.getId());
        LoginDetails loginDetails = new LoginDetails(newTeacher.getId(),newTeacher.getEmail(),
                newTeacher.getName(),newTeacher.getPassword(),Role.TEACHER);
        loginClient.signupUser(loginDetails);
        return newTeacher;
    }

    public UserEntity addParent(RegisterParentRequest registerParentRequest) {
        if(userCredentialRepository.existsByEmail(registerParentRequest.getEmail())){
            throw new RuntimeException("Try with a different mail id");
        }
        UserEntity newParent = new UserEntity();
        BeanUtils.copyProperties(registerParentRequest, newParent); //instead of getting and setting we can copy
        String hashedPassword = passwordEncoder.encode(registerParentRequest.getPassword());
        newParent.setPassword(hashedPassword);
        newParent.setRole(Role.PARENT);
        UserEntity createdUser = userCredentialRepository.save(newParent);
        newParent.setId(createdUser.getId());
        StudentEntity student =
                studentRepository.findById(registerParentRequest.getStudentId()).orElseThrow();
        student.setParent(newParent);
        studentRepository.save(student);
        LoginDetails loginDetails = new LoginDetails(newParent.getId(),newParent.getEmail(),
                newParent.getName(),newParent.getPassword(),Role.PARENT);
        loginClient.signupUser(loginDetails);
        return newParent;
    }
}
