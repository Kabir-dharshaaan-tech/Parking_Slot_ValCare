
package com.parking_slot_reservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "slot", uniqueConstraints = {@UniqueConstraint(columnNames = {"slot_number", "floor_id"})})
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_number")
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    // constructors, getters, setters
    public Slot() {}
    public Slot(String slotNumber, VehicleType vehicleType, Floor floor) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.floor = floor;
    }

    public Long getId() { return id; }
    public String getSlotNumber() { return slotNumber; }
    public void setSlotNumber(String slotNumber) { this.slotNumber = slotNumber; }
    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }
}
