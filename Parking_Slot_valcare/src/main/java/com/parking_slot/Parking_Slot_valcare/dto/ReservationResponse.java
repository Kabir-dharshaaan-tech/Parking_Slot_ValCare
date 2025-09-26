package com.parking_slot_reservation.dto;

import java.time.OffsetDateTime;

public class ReservationResponse {
    private Long id;
    private Long slotId;
    private String vehicleNumber;
    private String vehicleType;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private long hoursCharged;
    private double ratePerHour;
    private double totalAmount;
    private String status;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public OffsetDateTime getStartTime() { return startTime; }
    public void setStartTime(OffsetDateTime startTime) { this.startTime = startTime; }
    public OffsetDateTime getEndTime() { return endTime; }
    public void setEndTime(OffsetDateTime endTime) { this.endTime = endTime; }
    public long getHoursCharged() { return hoursCharged; }
    public void setHoursCharged(long hoursCharged) { this.hoursCharged = hoursCharged; }
    public double getRatePerHour() { return ratePerHour; }
    public void setRatePerHour(double ratePerHour) { this.ratePerHour = ratePerHour; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
