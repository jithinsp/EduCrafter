package com.corner.academics.controller;

import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.ClassesResponse;
import com.corner.academics.entity.Classes;
import com.corner.academics.service.classes.ClassesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("academics/classes/")
public class ClassesController {
    @Autowired
    ClassesService classesService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllClasses(){
        List<ClassesResponse> classList =  classesService.getAllClasses();
        return ResponseEntity.ok(classList);
    }

    @GetMapping("get/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable UUID classId){
        Classes classes =  classesService.getClassById(classId);
        return ResponseEntity.ok(classes);
    }

    @PostMapping("addNew")
    public ResponseEntity<?> registerNewClass(@RequestBody ClassesRequest classesRequest){
        try {
            Classes classes = classesService.registerNewClass(classesRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(classes);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("toggle/{classId}")
    public ResponseEntity<? > toggle(@PathVariable UUID classId){
        try {
            System.out.println(classId);
            Boolean success = classesService.toggleStatus(classId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("delete/{classId}")
    public ResponseEntity<? > softDelete(@PathVariable UUID classId){
        try {
            System.out.println(classId);
            Boolean success = classesService.deleteClass(classId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("editClass/{id}")
    public ResponseEntity<?> editClass(@PathVariable UUID id,
                                      @RequestBody ClassesRequest classesRequest) {
        try {
            Classes updatedClass = classesService.updateClass(id,classesRequest);
            return ResponseEntity.ok(updatedClass);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
