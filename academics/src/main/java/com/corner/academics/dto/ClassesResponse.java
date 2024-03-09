package com.corner.academics.dto;

import com.corner.academics.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesResponse{
    private UUID id;
    private String classes;
    private String division;
    private String classTeacher;
    private String remarks;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private Boolean isEnabled;
    private Boolean isDeleted;
}
