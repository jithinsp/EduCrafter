package com.corner.academics.service.slot;

import com.corner.academics.dto.SlotRequest;
import com.corner.academics.entity.Slot;

import java.util.List;
import java.util.UUID;

public interface SlotService {
    List<Slot> getAllSlots();

    Slot getSlotById(UUID slotId);

    Slot registerNewSlot(SlotRequest slotRequest);

    Boolean toggleStatus(UUID slotId);

    Slot updateSlot(UUID id, SlotRequest slotRequest);

    Boolean deleteSlot(UUID slotId);
}
