package com.corner.user.client;

import com.corner.user.dto.LoginDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface LoginClient {
    @PostExchange("auth/signup")
    public ResponseEntity<?> signupUser(@RequestBody LoginDetails signupRequest);
}
