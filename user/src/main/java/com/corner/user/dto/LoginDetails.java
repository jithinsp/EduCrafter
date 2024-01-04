package com.corner.user.dto;

import com.corner.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class LoginDetails {
    private UUID id;
    private String email;
    private String name;
    private String password;
    private Role role;
}
