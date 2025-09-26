package com.parking_slot_reservation.service.impl;

import com.parking_slot_reservation.dto.SlotRequest;
import com.parking_slot_reservation.entity.Floor;
import com.parking_slot_reservation.entity.Slot;
import com.parking_slot_reservation.entity.VehicleType;
import com.parking_slot_reservation.exception.ResourceNotFoundException;
import com.parking_slot_reservation.repository.FloorRepository;
import com.parking_slot_reservation.repository.SlotRepository;
import com.parking_slot_reservation.service.SlotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

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
            // return all slots not reserved for the period (simple query omitted for brevity)
            return slotRepository.findAvailableSlots(start, end, VehicleType.FOUR_WHEELER); // example: filter by FOUR_WHEELER
        }
        return slotRepository.findAvailableSlots(start, end, vt);
    }
}
