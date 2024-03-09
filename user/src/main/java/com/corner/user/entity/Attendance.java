package com.corner.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime date;
    private boolean isAttended;
    @ManyToOne
    @ToString.Exclude
    private StudentEntity student;

//    @ManyToOne
//    @ToString.Exclude
//    private UUID studentId;

//    @Override
//    public String toString() {
//        return "Attendance{" +
//                "id=" + id +
//                ", date=" + date +
//                ", isAttended=" + isAttended +
//                '}';
//    }
}