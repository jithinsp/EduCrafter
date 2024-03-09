package com.corner.user.controller;

import com.corner.user.entity.Role;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import com.corner.user.service.student.StudentService;
import com.corner.user.service.user.UserCredentialService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/admin")
public class AdminController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
//
//    @GetMapping("/hi")
//    public HelloResponse adminMsg(){
//        return new HelloResponse("Welcome back admin");
//    }
    @Autowired
    UserCredentialService userCredentialService;
    @Autowired
    StudentService studentService;

    @GetMapping("/allTeachers")
    public ResponseEntity<List<UserEntity>> getAllTeachers(){
        List<UserEntity> users = userCredentialService.getAllByRole(Role.TEACHER);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/teacher/{userId}")
    public ResponseEntity<UserEntity> getTeacher(@PathVariable UUID userId) {
        UserEntity user = userCredentialService.getById(userId);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/allAdmins")
    public ResponseEntity<List<UserEntity>> getAllAdmins(){
        List<UserEntity> users = userCredentialService.getAllByRole(Role.ADMIN);
        return ResponseEntity.ok(users);
    }



//    @DeleteMapping("/delete/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
//        System.out.println("deleting"+userId);
//        userService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }
//    @GetMapping("/hii")
//    public String hii(){
//        return register.hello();
//    }

//    @GetMapping("/hello")
//    public String hello(@AuthenticationPrincipal(expression = "username") String email){
//        UserCredential users = userService.getUserByEmail(email);
//        LOGGER.info("name: " + users.getName());
//        return ("Hello " +users.getName());
//    }
//
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request){
        String username = userCredentialService.extractUsername(request);

        UserEntity users = userCredentialService.getUserByEmail(username);
        if(users!=null){
            return ResponseEntity.ok(users);
        } else {
            StudentEntity student =studentService.getUserByEmail(username);
            return ResponseEntity.ok(student);
        }
//        LOGGER.info("users" + users);
//        System.out.println(users);
//        return ResponseEntity.ok(users);
    }
}
