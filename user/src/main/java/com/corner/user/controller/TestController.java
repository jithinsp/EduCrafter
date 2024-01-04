package com.corner.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
    @GetMapping("test/{userName}")
    public String hi(@PathVariable String userName) {
        System.out.println("hi" + userName);
        return ("Success" + userName); // Return a response indicating success
    }

//    @GetMapping("/hello")
//    public String hello(@AuthenticationPrincipal(expression = "username") String email){
//        UserCredential users = userService.getUserByEmail(email);
//        LOGGER.info("name: " + users.getName());
//        return ("Hello " +users.getName());
//    }
}
