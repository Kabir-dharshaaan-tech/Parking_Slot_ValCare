package com.parking_slot.Parking_Slot_valcare.service.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking_slot.Parking_Slot_valcare.dto.SlotRequest;
import com.parking_slot.Parking_Slot_valcare.entity.Floor;
import com.parking_slot.Parking_Slot_valcare.entity.Slot;
import com.parking_slot.Parking_Slot_valcare.entity.VehicleType;
import com.parking_slot.Parking_Slot_valcare.exception.ResourceNotFoundException;
import com.parking_slot.Parking_Slot_valcare.repository.FloorRepository;
import com.parking_slot.Parking_Slot_valcare.repository.SlotRepository;
import com.parking_slot.Parking_Slot_valcare.service.SlotService;

@Service
@Transactional
public class SlotServiceImpl implements SlotService {
    private final SlotRepository slotRepository;
    private final FloorRepository floorRepository;

    public SlotServiceImpl(SlotRepository slotRepository, FloorRepository floorRepository) {
        this.slotRepository = slotRepository;
        this.floorRepository = floorRepository;
    }

    @Override
    public Slot createSlot(SlotRequest request) {
        Floor floor = floorRepository.findById(request.getFloorId())
                .orElseThrow(() -> new ResourceNotFoundException("Floor not found: " + request.getFloorId()));
        VehicleType vt = VehicleType.valueOf(request.getVehicleType());
        Slot s = new Slot(request.getSlotNumber(), vt, floor);
        return slotRepository.save(s);
    }

    @Override
    public List<Slot> getSlotsByFloor(Long floorId) {
        return slotRepository.findByFloorId(floorId);
    }

    @Override
    public List<Slot> findAvailableSlots(OffsetDateTime start, OffsetDateTime end, String vehicleType) {
        VehicleType vt = vehicleType == null ? null : VehicleType.valueOf(vehicleType);
        if (vt == null) {
           
            return slotRepository.findAvailableSlots(start, end, VehicleType.FOUR_WHEELER); 
        }
        return slotRepository.findAvailableSlots(start, end, vt);
    }
}
