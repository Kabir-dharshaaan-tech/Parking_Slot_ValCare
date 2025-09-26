package com.parking_slot.Parking_Slot_valcare.controller;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking_slot.Parking_Slot_valcare.dto.SlotRequest;
import com.parking_slot.Parking_Slot_valcare.entity.Slot;
import com.parking_slot.Parking_Slot_valcare.service.SlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Slot> createSlot(@Valid @RequestBody SlotRequest request) {
        Slot s = slotService.createSlot(request);
        return ResponseEntity.created(URI.create("/slots/" + s.getId())).body(s);
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getSlots(@RequestParam(value = "floorId", required = false) Long floorId) {
        if (floorId == null) {
            // Optional: replace List.of() with slotService.getAllSlots() if you have that method
            return ResponseEntity.ok(List.of());
        } else {
            return ResponseEntity.ok(slotService.getSlotsByFloor(floorId));
        }
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Slot>> availability(
            @RequestParam("start") OffsetDateTime start,
            @RequestParam("end") OffsetDateTime end,
            @RequestParam(value = "vehicleType", required = false) String vehicleType) {
        return ResponseEntity.ok(slotService.findAvailableSlots(start, end, vehicleType));
    }
}
