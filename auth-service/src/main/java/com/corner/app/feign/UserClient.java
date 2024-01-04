//package com.corner.app.feign;
//
//import com.corner.app.dto.RegisterRequest;
//import com.corner.app.entity.UserCredential;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.service.annotation.HttpExchange;
//import org.springframework.web.service.annotation.PostExchange;
//
//@HttpExchange
//public interface UserClient {
//    @PostExchange("/register")
//    public UserCredential registerUser(@RequestBody RegisterRequest registerRequest);
//}
