package com.corner.academics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@AttributeOverride(name = "id", column = @Column(name = "id"))
public class StudentInfo extends BaseEntity{

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String division;
//    @OneToMany(mappedBy = "studentInfo")
//    @ToString.Exclude
//    private List<Attendance> attendances = new ArrayList<>();
//
//    @Override
//    public String toString() {
//        return "StudentInfo{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", division='" + division + '\'' +
//                '}';
//    }

}