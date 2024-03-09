package com.corner.academics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class SlotRequest{
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
