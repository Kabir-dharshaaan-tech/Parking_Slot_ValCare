package com.parking_slot_reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking_slot_reservation.entity.Slot;
import com.parking_slot_reservation.entity.VehicleType;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByFloorId(Long floorId);

    @Query("""
      select s from Slot s
      where s.vehicleType = :vehicleType
      and s.id not in (
        select r.slot.id from Reservation r
        where r.status = 'CONFIRMED'
        and r.startTime < :end and r.endTime > :start
      )
      """)
    List<Slot> findAvailableSlots(java.time.OffsetDateTime start, java.time.OffsetDateTime end, VehicleType vehicleType);
}
