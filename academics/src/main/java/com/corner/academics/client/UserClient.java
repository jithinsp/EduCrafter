package com.corner.academics.client;

import com.corner.academics.dto.UserEntityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.UUID;

@HttpExchange
public interface UserClient {
//    @PostExchange("/register")
//    public UserCredential registerUser(@RequestBody RegisterRequest registerRequest);

    @GetExchange("user/admin/teacher/{userId}")
    public ResponseEntity<UserEntityResponse> getTeacher(@PathVariable UUID userId);


}