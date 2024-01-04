package com.corner.user.service.student;

import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;

public interface StudentService {
    StudentEntity addStudent(RegisterStudentRequest registerStudentRequest);
}
