package com.corner.academics.service.classes;

import com.corner.academics.client.UserClient;
import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.ClassesResponse;
import com.corner.academics.dto.UserEntityResponse;
import com.corner.academics.entity.Classes;
import com.corner.academics.repository.ClassesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClassesServiceImpl implements ClassesService{
    @Autowired
    ClassesRepository classesRepository;
    @Autowired
    UserClient userClient;

    public List<ClassesResponse> getAllClasses() {
        List<Classes> classesList = classesRepository.findAll();
        List<ClassesResponse> classesResponses = new ArrayList<>();
        System.out.println("Here");
        for (Classes classes : classesList) {
            System.out.println(classes);
            UUID userId = classes.getClassTeacherId();
            UserEntityResponse teacher = userClient.getTeacher(userId).getBody();
            System.out.println(teacher);
            ClassesResponse classesResponse = new ClassesResponse();
            classesResponse.setId(classes.getId());
            classesResponse.setClassTeacher(teacher.getName());
            classesResponse.setClasses(classes.getClasses());
            classesResponse.setDivision(classes.getDivision());
            classesResponse.setRemarks(classes.getRemarks());

            classesResponse.setIsDeleted(classes.getIsDeleted());
            classesResponse.setIsEnabled(classes.getIsEnabled());
            classesResponse.setCreatedDate(classes.getCreatedDate());
            classesResponse.setLastModifiedDate(classes.getLastModifiedDate());

            classesResponses.add(classesResponse);
        }

        return classesResponses;
    }



    public Classes registerNewClass(ClassesRequest classesRequest) {
        Classes classes = new Classes();
        BeanUtils.copyProperties(classesRequest,classes);
        return classesRepository.save(classes);
    }

    public Boolean toggleStatus(UUID classId) {
        Classes classes = classesRepository
                .findById(classId)
                .orElseThrow(()->new EntityNotFoundException("Class with id: "+classId +
                        " Not present"));
        classes.setIsEnabled(classes.getIsEnabled() != null && !classes.getIsEnabled());
//        classes.setIsEnabled(classes.getIsEnabled()!= null ? !classes.getIsEnabled() : false);
        Classes cls = classesRepository.save(classes);
        return cls.getIsEnabled();
    }

    public Classes getClassById(UUID classId) {
        return classesRepository.findById(classId)
                .orElseThrow(()->new EntityNotFoundException("Grade not found"));
    }

    public Classes updateClass(UUID id, ClassesRequest classesRequest) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Grade not found"));
        classes.setClassTeacherId(classesRequest.getClassTeacherId());
        classes.setClasses(classesRequest.getClasses());
        classes.setDivision(classesRequest.getDivision());
        classes.setRemarks(classesRequest.getRemarks());
        return classesRepository.save(classes);
    }

    public Boolean deleteClass(UUID classId) {
        Classes classes = classesRepository
                .findById(classId)
                .orElseThrow(()->new EntityNotFoundException("Class with id: "+classId+" " +
                        "Not present"));
        classes.setIsDeleted(!classes.getIsDeleted());
        Classes cls = classesRepository.save(classes);
        return cls.getIsDeleted();
    }
}
