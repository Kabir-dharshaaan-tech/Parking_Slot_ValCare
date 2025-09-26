package com.parking_slot_reservation.repository;

import com.parking_slot_reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.slot.id = :slotId and r.status = 'CONFIRMED' and r.startTime < :end and r.endTime > :start")
    List<Reservation> findOverlapping(Long slotId, OffsetDateTime start, OffsetDateTime end);

    List<Reservation> findBySlotId(Long slotId);
}
