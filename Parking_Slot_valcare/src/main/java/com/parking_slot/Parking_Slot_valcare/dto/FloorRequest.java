package com.parking_slot_reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class FloorRequest {
    @NotBlank
    private String floorName;

    @NotNull @PositiveOrZero
    private Integer level;

    // getters & setters
    public String getFloorName() { return floorName; }
    public void setFloorName(String floorName) { this.floorName = floorName; }
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
}
