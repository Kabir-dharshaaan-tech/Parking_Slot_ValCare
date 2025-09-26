package com.parking_slot.Parking_Slot_valcare.entity;

public enum VehicleType {
    FOUR_WHEELER(30.0),
    TWO_WHEELER(20.0);

    private final double ratePerHour;

    VehicleType(double rate) { this.ratePerHour = rate; }

    public double getRatePerHour() { return ratePerHour; }
}
