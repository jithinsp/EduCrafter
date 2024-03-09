package com.corner.academics.dto;

import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Slot;
import com.corner.academics.entity.Subjects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExamRequest{

    private String name;
    @ManyToOne
    private Subjects subject;
    @ManyToOne
    private Classes classes;
    @ManyToOne
    private Slot timeslot;
    private LocalDate date;
}
