package com.corner.app.service.impl;

import com.corner.app.dto.RegisterRequest;
import com.corner.app.dto.SignupRequest;
import com.corner.app.entity.Role;
import com.corner.app.entity.UserCredential;
//import com.corner.app.feign.UserClient;
import com.corner.app.repository.UserRepository;
import com.corner.app.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
//    @Autowired
//    UserClient userClient;

    @Override
    public UserCredential createUser(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return null;
        }
//        boolean studentRoleExists = userRepository.existsByRole(Role.STUDENT);
//        if (studentRoleExists) {
//            // Student role exists in the repository
//            // Your logic here
//        } else {
//            // Student role does not exist in the repository
//            // Create and save the role
//
////            roleRepository.save(newRole);
//            // Your further logic if needed
//        }

//        Optional<Role> userRoleOptional = userRepository.findRoleByName(Role.STUDENT.name());
//        Role userRole = userRoleOptional.orElseGet(()->{
//            Role newRole = Role.STUDENT;
//            return roleRepository.save(newRole);
//        });
//        Role newRole = Role.STUDENT;
        System.out.println("here");
        UserCredential users = new UserCredential();
        BeanUtils.copyProperties(signupRequest, users); //instead of getting and setting we can copy
//        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
//        users.setPassword(hashedPassword);
//        users.setRole(Role.STUDENT);
        UserCredential createdUser = userRepository.save(users);
//        users.setId(createdUser.getId());
//        RegisterRequest registerRequest = new RegisterRequest(
//                users.getId(),users.getEmail(), users.getName(),  users.getPassword()
//        );
//        userClient.registerUser(registerRequest);
//        System.out.println("successfully registered");
        return users;
    }
}
