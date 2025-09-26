package com.parking_slot_reservation.service;

import com.parking_slot_reservation.dto.FloorRequest;
import com.parking_slot_reservation.entity.Floor;

import java.util.List;

public interface FloorService {
    Floor createFloor(FloorRequest request);
    List<Floor> getAllFloors();
    Floor getFloor(Long id);
}
