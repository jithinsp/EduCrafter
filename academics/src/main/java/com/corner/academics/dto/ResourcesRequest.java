package com.corner.academics.dto;

import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Subjects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ResourcesRequest{
    private UUID subjectId;
    private UUID classesId;
    private MultipartFile file;
}
