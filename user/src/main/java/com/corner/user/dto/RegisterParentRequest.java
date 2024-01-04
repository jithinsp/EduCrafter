package com.corner.user.dto;

import com.corner.user.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterParentRequest {
    private String email;
    private String name;
    private String password;
    private LocalDateTime dateOfBirth;
    private Sex sex;
    private String phone;
    private UUID studentId;
    private String remarks;
}
