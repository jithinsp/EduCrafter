package com.corner.user.client;


import com.corner.user.dto.RegisterStudentInAcademics;
import com.corner.user.dto.RegisterStudentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface AcademicsService {
    @PostExchange("academics/student/signup")
    public ResponseEntity<?> signupUser(@RequestBody RegisterStudentInAcademics request);
}
