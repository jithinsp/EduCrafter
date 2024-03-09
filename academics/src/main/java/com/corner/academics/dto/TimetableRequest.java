package com.corner.academics.dto;

import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Slot;
import com.corner.academics.entity.Subjects;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TimetableRequest{
    private Classes classes;
    private Subjects subjects;
    private Slot slot;
    private UUID teacherId;
}
