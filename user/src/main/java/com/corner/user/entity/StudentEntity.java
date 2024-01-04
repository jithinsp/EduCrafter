package com.corner.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    private UserEntity parent;
    private String remarks;
}
