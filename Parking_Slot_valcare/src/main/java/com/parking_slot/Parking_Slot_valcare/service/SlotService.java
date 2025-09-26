package com.parking_slot.Parking_Slot_valcare.service;

import java.time.OffsetDateTime;
import java.util.List;

import com.parking_slot.Parking_Slot_valcare.dto.SlotRequest;
import com.parking_slot.Parking_Slot_valcare.entity.Slot;

public interface SlotService {
    Slot createSlot(SlotRequest request);
    List<Slot> getSlotsByFloor(Long floorId);
    List<Slot> findAvailableSlots(OffsetDateTime start, OffsetDateTime end, String vehicleType);
}
