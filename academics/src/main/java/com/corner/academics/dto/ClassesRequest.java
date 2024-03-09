package com.corner.academics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ClassesRequest{
    private String classes;
    private String division;
    private UUID classTeacherId;
    private String remarks;
}
