package com.parking_slot.Parking_Slot_valcare.service;


import com.parking_slot.Parking_Slot_valcare.dto.FloorRequest;
import com.parking_slot.Parking_Slot_valcare.entity.Floor;

import java.util.List;

public interface FloorService {
    Floor createFloor(FloorRequest request);
    List<Floor> getAllFloors();
    Floor getFloor(Long id);
}
