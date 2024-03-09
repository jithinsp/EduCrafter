package com.corner.academics.service.student;

import com.corner.academics.dto.RegisterStudentRequest;
import com.corner.academics.entity.StudentInfo;

import java.util.List;

public interface StudentInfoService {
    StudentInfo createUser(RegisterStudentRequest registerStudentRequest);
    List<StudentInfo> getAllStudents();

}
