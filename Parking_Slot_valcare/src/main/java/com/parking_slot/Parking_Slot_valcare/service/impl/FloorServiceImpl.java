

package com.parking_slot_reservation.service.impl;

import com.parking_slot_reservation.dto.FloorRequest;
import com.parking_slot_reservation.entity.Floor;
import com.parking_slot_reservation.exception.ResourceNotFoundException;
import com.parking_slot_reservation.repository.FloorRepository;
import com.parking_slot_reservation.service.FloorService;
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
        return floorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Floor not found: " + id));
    }
}
