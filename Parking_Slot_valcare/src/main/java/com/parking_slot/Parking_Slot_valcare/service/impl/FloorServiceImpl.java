package com.parking_slot.Parking_Slot_valcare.service.impl;

import com.parking_slot.Parking_Slot_valcare.dto.FloorRequest;
import com.parking_slot.Parking_Slot_valcare.entity.Floor;
import com.parking_slot.Parking_Slot_valcare.exception.ResourceNotFoundException;
import com.parking_slot.Parking_Slot_valcare.repository.FloorRepository;
import com.parking_slot.Parking_Slot_valcare.service.FloorService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public Floor createFloor(FloorRequest request) {
        Floor f = new Floor(request.getFloorName(), request.getLevel());
        return floorRepository.save(f);
    }

    @Override
    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    @Override
    public Floor getFloor(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Floor not found: " + id));
    }
}
