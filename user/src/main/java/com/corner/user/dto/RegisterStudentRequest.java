package com.corner.user.dto;

import com.corner.user.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegisterStudentRequest {
    private String email;
    private String name;
    private String password;
    private LocalDateTime dateOfBirth;
    private Sex sex;
    private String phone;
    private String remarks;
}
