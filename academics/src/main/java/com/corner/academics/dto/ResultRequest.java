package com.corner.academics.dto;

import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Exam;
import com.corner.academics.entity.Subjects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ResultRequest{
    @ManyToOne
    private Subjects subject;
    @ManyToOne
    private Classes classes;
    @ManyToOne
    private Exam exam;
    private UUID studentId;
    private Double mark;
}
