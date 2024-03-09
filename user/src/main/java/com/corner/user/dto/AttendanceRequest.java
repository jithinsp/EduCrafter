package com.corner.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AttendanceRequest {
    private UUID userId;
    private Boolean isPresent;
}
