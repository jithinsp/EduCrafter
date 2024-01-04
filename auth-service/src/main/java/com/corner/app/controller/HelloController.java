//package com.corner.app.controller;
//
//import com.corner.app.entity.UserCredential;
//
//import com.corner.app.service.impl.UserServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/auth")
//public class HelloController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
//
////    @Autowired
////    Register register;
//
//    @Autowired
//    UserServiceImpl userService;
//
////    @GetMapping("/hii")
////    public String hii(){
////        return register.hello();
////    }
//
//    @GetMapping("/hello")
//    public String hello(@AuthenticationPrincipal(expression = "username") String email){
//        UserCredential users = userService.getUserByEmail(email);
//        LOGGER.info("name: " + users.getName());
//        return ("Hello " +users.getName());
//    }
//
//    @GetMapping("/profile")
//    public ResponseEntity<UserCredential> getAllUsers(@AuthenticationPrincipal(expression = "username") String email){
//        UserCredential users = userService.getUserByEmail(email);
//        LOGGER.info("users" + users);
//        System.out.println(users);
//        return ResponseEntity.ok(users);
//    }
//}
