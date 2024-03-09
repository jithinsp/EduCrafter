package com.corner.app.controller;

import com.corner.app.dto.LoginRequest;
import com.corner.app.dto.LoginResponse;
import com.corner.app.dto.SignupRequest;
import com.corner.app.entity.UserCredential;
import com.corner.app.service.impl.UserServiceImpl;
import com.corner.app.utility.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    JwtUtil jwtUtil;


//    @PostMapping
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
//                            loginRequest.getPassword())
//            );
//        } catch (AuthenticationException e){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        UserDetails userDetails;
//        try{
//            userDetails = userService.loadUserByUsername(loginRequest.getEmail());
//        } catch (UsernameNotFoundException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        String jwt = jwtUtil.generateToken(userDetails.getUsername());
//        return ResponseEntity.ok(new LoginResponse(jwt));
//    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws
            IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login failed due to bad credentials");
            System.out.println("Login failed due to bad credentials");
            return null;
        } catch (DisabledException disabledException) {
            System.out.println("Login failed due to customer is not activated");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Customer is not activated");
            return null;
        }
        LOGGER.info("Login");

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(),
                userDetails.getAuthorities().toString());

        return new LoginResponse(jwt);
    }

//    @PostMapping
//    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
//        System.out.println(signupRequest);
//        UserCredential user = authService.createUser(signupRequest);
//        if (user != null){
//            return ResponseEntity.status(HttpStatus.CREATED).body(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
//        }
//    }

    @GetMapping("/users")
    public ResponseEntity<List<UserCredential>> getAllUsers(){
        List<UserCredential> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        System.out.println("deleting"+userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
