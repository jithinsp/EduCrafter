package com.corner.user.controller;

import com.corner.user.entity.UserEntity;
import com.corner.user.service.user.UserCredentialService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/parent")
public class ParentController {
    @Autowired
    UserCredentialService userCredentialService;

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> getUserProfile(HttpServletRequest request){
        String username = userCredentialService.extractUsername(request);
        UserEntity users = userCredentialService.getUserByEmail(username);
        System.out.println(users);
        return ResponseEntity.ok(users);
    }
}