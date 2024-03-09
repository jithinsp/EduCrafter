package com.corner.academics.controller;

import com.corner.academics.dto.ResultRequest;
import com.corner.academics.entity.Result;
import com.corner.academics.service.result.ResultService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("academics/results")
public class ResultController {
    @Autowired
    ResultService resultService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllResults(){
        List<Result> results =  resultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("get/{resultId}")
    public ResponseEntity<?> getResultById(@PathVariable UUID resultId){
        Result result =  resultService.getResultById(resultId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("addNew")
    public ResponseEntity<?> registerNewResult(@RequestBody ResultRequest resultRequest){
        try {
            Result result = resultService.registerNewResult(resultRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("toggle/{resultId}")
    public ResponseEntity<?> toggleStatus(@PathVariable UUID resultId){
        try {
            Boolean success = resultService.toggleStatus(resultId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("delete/{resultId}")
    public ResponseEntity<?> softDelete(@PathVariable UUID resultId){
        try {
            Boolean success = resultService.deleteResult(resultId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editResult(@PathVariable UUID id,
                                      @RequestBody ResultRequest resultRequest) {
        try {
            Result updatedResult = resultService.updateResult(id,resultRequest);
            return ResponseEntity.ok(updatedResult);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}