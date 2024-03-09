package com.corner.academics.controller;

import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.SubjectRequest;
import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Subjects;
import com.corner.academics.service.subject.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("academics/subjects/")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllSubjects(){
        List<Subjects> subjectList =  subjectService.getAllSubjects();
        return ResponseEntity.ok(subjectList);
    }

    @GetMapping("get/{subjectId}")
    public ResponseEntity<?> getSubjectById(@PathVariable UUID subjectId){
        Subjects subjects =  subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("addNew")
    public ResponseEntity<?> registerNewSubject(@RequestBody SubjectRequest subjectRequest){
        try {
            Subjects subjects = subjectService.registerNewSubject(subjectRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(subjects);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("toggle/{subjectId}")
    public ResponseEntity<?> toggle(@PathVariable UUID subjectId){
        try {
            Boolean status = subjectService.toggleStatus(subjectId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("delete/{subjectId}")
    public ResponseEntity<?> softDelete(@PathVariable UUID subjectId){
        try {
            Boolean status = subjectService.deleteSubject(subjectId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/editSubject/{id}")
    public ResponseEntity<?> editSubject(@PathVariable UUID id,
                                       @RequestBody SubjectRequest subjectRequest) {
        try {
            Subjects updatedSubject = subjectService.updateSubject(id,subjectRequest);
            return ResponseEntity.ok(updatedSubject);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
