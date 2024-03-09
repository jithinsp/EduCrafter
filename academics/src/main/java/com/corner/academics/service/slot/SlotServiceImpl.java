package com.corner.academics.service.slot;

import com.corner.academics.dto.SlotRequest;
import com.corner.academics.entity.Slot;
import com.corner.academics.repository.SlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SlotServiceImpl implements SlotService{
    @Autowired
    SlotRepository slotRepository;

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Slot getSlotById(UUID slotId) {
        return slotRepository.findById(slotId)
                .orElseThrow(()->new EntityNotFoundException("Slot not found"));
    }

    public Slot registerNewSlot(SlotRequest slotRequest) {
        Slot newSlot = new Slot();
        BeanUtils.copyProperties(slotRequest,newSlot);
        return slotRepository.save(newSlot);
    }

    public Boolean toggleStatus(UUID slotId) {
        Slot toggleSlot = slotRepository.findById(slotId)
                .orElseThrow(()->new EntityNotFoundException("Slot not found"));
        toggleSlot.setIsEnabled(!toggleSlot.getIsEnabled());
        slotRepository.save(toggleSlot);
        return toggleSlot.getIsDeleted();
    }

    public Slot updateSlot(UUID id, SlotRequest slotRequest) {
        Slot updateSlot = slotRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Slot not found"));
        updateSlot.setName(slotRequest.getName());
        updateSlot.setStartTime(slotRequest.getStartTime());
        updateSlot.setEndTime(slotRequest.getEndTime());
        return slotRepository.save(updateSlot);
    }

    public Boolean deleteSlot(UUID slotId) {
        Slot toggleSlot = slotRepository.findById(slotId)
                .orElseThrow(()->new EntityNotFoundException("Slot not found"));
        toggleSlot.setIsDeleted(!toggleSlot.getIsDeleted());
        slotRepository.save(toggleSlot);
        return toggleSlot.getIsDeleted();
    }

}
