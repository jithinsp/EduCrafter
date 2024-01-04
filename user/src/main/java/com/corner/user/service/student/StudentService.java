package com.corner.user.service.student;

import com.corner.user.dto.RegisterStudentRequest;
import com.corner.user.entity.Role;
import com.corner.user.entity.StudentEntity;
import com.corner.user.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface StudentService {
    StudentEntity addStudent(RegisterStudentRequest registerStudentRequest);

    List<StudentEntity> getAllByRole(Role student);
    public String extractUsername(HttpServletRequest request);
    public StudentEntity getUserByEmail(String email);
}
