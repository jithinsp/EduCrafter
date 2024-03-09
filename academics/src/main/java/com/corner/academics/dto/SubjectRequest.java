package com.corner.academics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectRequest {
    private String name;
    private String description;
}
