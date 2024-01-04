package com.corner.app.dto;

import com.corner.app.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SignupRequest {
    private UUID id;
    private String email;
    private String name;
    private String password;
    private Role role;
}
