package com.corner.academics.service.classes;

import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.ClassesResponse;
import com.corner.academics.entity.Classes;

import java.util.List;
import java.util.UUID;

public interface ClassesService {
    List<ClassesResponse> getAllClasses();

    Classes registerNewClass(ClassesRequest classesRequest);

    Boolean toggleStatus(UUID classId);

    Classes getClassById(UUID classId);

    Classes updateClass(UUID id, ClassesRequest classesRequest);

    Boolean deleteClass(UUID classId);
}
