//package com.corner.app.controller;
//
//import com.corner.app.entity.UserCredential;
//import com.corner.app.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/auth/validate")
//public class ValidateController {
//
//    @Autowired
//    UserServiceImpl userService;
//
//    @GetMapping
//    public String hello(@AuthenticationPrincipal(expression = "username") String email){
//        UserCredential users = userService.getUserByEmail(email);
////        LOGGER.info("name: " + users.getName());
//        return ("Hello " +users.getName());
//    }
//}
