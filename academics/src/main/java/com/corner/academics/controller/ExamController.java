package com.corner.academics.controller;

import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.ExamRequest;
import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Exam;
import com.corner.academics.service.exam.ExamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("academics/exam/")
public class ExamController {
    @Autowired
    ExamService examService;
    @GetMapping("getAll")
    public ResponseEntity<?> getAllExams(){
        List<Exam> exams =  examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("get/{examId}")
    public ResponseEntity<?> getExamById(@PathVariable UUID examId){
        Exam exam =  examService.getExamById(examId);
        return ResponseEntity.ok(exam);
    }

    @PostMapping("addNew")
    public ResponseEntity<?> registerNewExam(@RequestBody ExamRequest examRequest){
        try {
            Exam exam = examService.registerNewExam(examRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(exam);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("toggle/{examId}")
    public ResponseEntity<?> toggleStatus(@PathVariable UUID examId){
        try {
            Boolean success = examService.toggleStatus(examId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("delete/{examId}")
    public ResponseEntity<?> softDelete(@PathVariable UUID examId){
        try {
            Boolean success = examService.deleteExam(examId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editExam(@PathVariable UUID id,
                                       @RequestBody ExamRequest examRequest) {
        try {
            Exam updatedExam = examService.updateExam(id,examRequest);
            return ResponseEntity.ok(updatedExam);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
