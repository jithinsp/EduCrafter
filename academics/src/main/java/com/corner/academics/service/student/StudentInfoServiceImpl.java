package com.corner.academics.service.student;

import com.corner.academics.dto.RegisterStudentRequest;
import com.corner.academics.entity.StudentInfo;
import com.corner.academics.repository.StudentInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements StudentInfoService{
    @Autowired
    StudentInfoRepository studentInfoRepository;

    public StudentInfo createUser(RegisterStudentRequest registerStudentRequest) {
        StudentInfo student = new StudentInfo();
        BeanUtils.copyProperties(registerStudentRequest, student);
        return studentInfoRepository.save(student);
    }

    public List<StudentInfo> getAllStudents(){
        List<StudentInfo> students = studentInfoRepository.findAll();
        System.out.println(students);
        return students;
    }
}
