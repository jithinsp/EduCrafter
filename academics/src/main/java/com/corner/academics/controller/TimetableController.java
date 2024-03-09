package com.corner.academics.controller;

import com.corner.academics.dto.SubjectRequest;
import com.corner.academics.dto.TimetableRequest;
import com.corner.academics.entity.Subjects;
import com.corner.academics.entity.Timetable;
import com.corner.academics.service.timetable.TimetableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/academics/timetable/")
public class TimetableController {
    @Autowired
    TimetableService timetableService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllTimetable(){
        List<Timetable> timetableList =  timetableService.getAllTimetable();
        return ResponseEntity.ok(timetableList);
    }

    @GetMapping("get/{timetableId}")
    public ResponseEntity<?> getTimetableById(@PathVariable UUID timetableId){
        Timetable timetable =  timetableService.getTimetableById(timetableId);
        return ResponseEntity.ok(timetable);
    }

    @GetMapping("get/{classId}")
    public ResponseEntity<?> getTimetableByClass(@PathVariable UUID classId){
        Timetable timetable =  timetableService.getTimetableByClass(classId);
        return ResponseEntity.ok(timetable);
    }

    @PostMapping("addNew")
    public ResponseEntity<?> registerNewTimetable(@RequestBody TimetableRequest timetableRequest){
        try {
            Timetable timetable = timetableService.registerNewTimetable(timetableRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(timetable);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("toggle/{timetableId}")
    public ResponseEntity<?> toggleStatus(@PathVariable UUID timetableId){
        try {
            Boolean status = timetableService.toggleStatus(timetableId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("delete/{timetableId}")
    public ResponseEntity<?> softDelete(@PathVariable UUID timetableId){
        try {
            Boolean status = timetableService.deleteTimetable(timetableId);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editTimetable(@PathVariable UUID id,
                                         @RequestBody TimetableRequest timetableRequest) {
        try {
            Timetable updatedTimetable = timetableService.updateTimetable(id,timetableRequest);
            return ResponseEntity.ok(updatedTimetable);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
