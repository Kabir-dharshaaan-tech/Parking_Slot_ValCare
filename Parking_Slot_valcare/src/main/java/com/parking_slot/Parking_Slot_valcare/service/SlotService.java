package com.parking_slot_reservation.service;

import java.time.OffsetDateTime;
import java.util.List;

import com.parking_slot_reservation.dto.SlotRequest;
import com.parking_slot_reservation.entity.Slot;

public interface SlotService {
    Slot createSlot(SlotRequest request);
    List<Slot> getSlotsByFloor(Long floorId);
    List<Slot> findAvailableSlots(OffsetDateTime start, OffsetDateTime end, String vehicleType);
}
