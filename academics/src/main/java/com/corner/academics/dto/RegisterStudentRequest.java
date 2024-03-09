package com.corner.academics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class RegisterStudentRequest {
    private UUID id;
    private String name;
    private String division;
}