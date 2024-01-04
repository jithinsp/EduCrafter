package com.corner.app.controller;

import com.corner.app.dto.RegisterRequest;
import com.corner.app.dto.SignupRequest;
import com.corner.app.entity.UserCredential;
//import com.corner.app.feign.UserClient;
import com.corner.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class SignupController {
    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        System.out.println(signupRequest);
        UserCredential user = authService.createUser(signupRequest);
        if (user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }

//    @PostMapping
//    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
//        boolean isUserCreated = authService.createUser(signupRequest);
//        if (isUserCreated){
//            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
//        }
//    }
}
