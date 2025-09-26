package com.parking_slot.Parking_Slot_valcare.controller;

import com.parking_slot.Parking_Slot_valcare.dto.FloorRequest;
import com.parking_slot.Parking_Slot_valcare.entity.Floor;
import com.parking_slot.Parking_Slot_valcare.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/floors")
public class FloorController {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @PostMapping
    public ResponseEntity<Floor> createFloor(@Valid @RequestBody FloorRequest request) {
        Floor created = floorService.createFloor(request);
        return ResponseEntity.created(URI.create("/floors/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Floor>> listFloors() {
        return ResponseEntity.ok(floorService.getAllFloors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Floor> getFloor(@PathVariable Long id) {
        return ResponseEntity.ok(floorService.getFloor(id));
    }
}
