package com.corner.academics.controller;

import com.corner.academics.dto.ClassesRequest;
import com.corner.academics.dto.SlotRequest;
import com.corner.academics.entity.Classes;
import com.corner.academics.entity.Slot;
import com.corner.academics.service.classes.ClassesService;
import com.corner.academics.service.slot.SlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("academics/slot/")
public class SlotController {
    @Autowired
    SlotService slotService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllSlots(){
        List<Slot> slots =  slotService.getAllSlots();
        return ResponseEntity.ok(slots);
    }

    @GetMapping("get/{slotId}")
    public ResponseEntity<?> getSlotById(@PathVariable UUID slotId){
        Slot slot =  slotService.getSlotById(slotId);
        return ResponseEntity.ok(slot);
    }

    @PostMapping("addNew")
    public ResponseEntity<?> registerNewSlot(@RequestBody SlotRequest slotRequest){
        try {
            Slot slot = slotService.registerNewSlot(slotRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(slot);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("toggle/{slotId}")
    public ResponseEntity<?> toggleStatus(@PathVariable UUID slotId){
        try {
            Boolean success = slotService.toggleStatus(slotId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("delete/{slotId}")
    public ResponseEntity<?> softDelete(@PathVariable UUID slotId){
        try {
            Boolean success = slotService.deleteSlot(slotId);
            return ResponseEntity.status(HttpStatus.OK).body(success);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editSlot(@PathVariable UUID id,
                                      @RequestBody SlotRequest slotRequest) {
        try {
            Slot updatedSlot = slotService.updateSlot(id,slotRequest);
            return ResponseEntity.ok(updatedSlot);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
