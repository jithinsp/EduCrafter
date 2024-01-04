package com.corner.user.entity;

import jakarta.persistence.*;
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
public class UserEntity extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String designation;
    private String remarks;
}
