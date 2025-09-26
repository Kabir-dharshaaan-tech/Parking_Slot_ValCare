package com.parking_slot.Parking_Slot_valcare.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    private long hoursCharged;
    private double ratePerHour;
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @Version
    private Long version;

    private String status = "CONFIRMED"; 

    public Reservation() {}

    public Reservation(String vehicleNumber, VehicleType vehicleType, OffsetDateTime startTime, OffsetDateTime endTime,
                       long hoursCharged, double ratePerHour, double totalAmount, Slot slot, String status) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hoursCharged = hoursCharged;
        this.ratePerHour = ratePerHour;
        this.totalAmount = totalAmount;
        this.slot = slot;
        this.status = status;
    }

    public Long getId() { return id; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }

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

    public Slot getSlot() { return slot; }
    public void setSlot(Slot slot) { this.slot = slot; }

    public Long getVersion() { return version; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
