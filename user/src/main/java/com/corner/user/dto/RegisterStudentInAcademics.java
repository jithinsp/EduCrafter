package com.corner.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterStudentInAcademics {
    private UUID id;
    private String name;
    private String division;
}
