package com.parking_slot_reservation.service.impl;

import com.parking_slot_reservation.dto.ReservationRequest;
import com.parking_slot_reservation.dto.ReservationResponse;
import com.parking_slot_reservation.entity.Reservation;
import com.parking_slot_reservation.entity.Slot;
import com.parking_slot_reservation.entity.VehicleType;
import com.parking_slot_reservation.exception.InvalidReservationException;
import com.parking_slot_reservation.exception.ResourceNotFoundException;
import com.parking_slot_reservation.repository.ReservationRepository;
import com.parking_slot_reservation.repository.SlotRepository;
import com.parking_slot_reservation.util.FeeCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements com.parking_slot_reservation.service.ReservationService {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, SlotRepository slotRepository) {
        this.reservationRepository = reservationRepository;
        this.slotRepository = slotRepository;
    }

    @Override
    public ReservationResponse createReservation(ReservationRequest req) {
        // basic validations
        if (!req.getStartTime().isBefore(req.getEndTime())) {
            throw new InvalidReservationException("startTime must be before endTime");
        }
        Duration dur = Duration.between(req.getStartTime(), req.getEndTime());
        if (dur.toMinutes() > 24 * 60) {
            throw new InvalidReservationException("Reservation duration cannot exceed 24 hours");
        }

        Slot slot = slotRepository.findById(req.getSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found: " + req.getSlotId()));

        VehicleType requested = VehicleType.valueOf(req.getVehicleType());
        if (slot.getVehicleType() != requested) {
            throw new InvalidReservationException("Slot does not support vehicle type " + requested);
        }

        // check availability (no overlapping)
        List<Reservation> overlaps = reservationRepository.findOverlapping(slot.getId(), req.getStartTime(), req.getEndTime());
        if (!overlaps.isEmpty()) {
            throw new InvalidReservationException("Slot already reserved during requested time");
        }

        long hours = FeeCalculator.calculateHours(req.getStartTime(), req.getEndTime());
        double rate = requested.getRatePerHour();
        double total = FeeCalculator.calculateTotal(hours, rate);

        Reservation r = new Reservation();
        r.setSlot(slot);
        r.setVehicleNumber(req.getVehicleNumber());
        r.setVehicleType(requested);
        r.setStartTime(req.getStartTime());
        r.setEndTime(req.getEndTime());
        r.setHoursCharged(hours);
        r.setRatePerHour(rate);
        r.setTotalAmount(total);
        r.setStatus("CONFIRMED");

        Reservation saved = reservationRepository.save(r);
        return toResponse(saved);
    }

    @Override
    public ReservationResponse getReservation(Long id) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found: " + id));
        return toResponse(r);
    }

    @Override
    public void cancelReservation(Long id) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found: " + id));
        r.setStatus("CANCELLED");
        reservationRepository.save(r);
    }

    @Override
    public List<ReservationResponse> listReservations() {
        return reservationRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private ReservationResponse toResponse(Reservation r) {
        ReservationResponse resp = new ReservationResponse();
        resp.setId(r.getId());
        resp.setSlotId(r.getSlot().getId());
        resp.setVehicleNumber(r.getVehicleNumber());
        resp.setVehicleType(r.getVehicleType().name());
        resp.setStartTime(r.getStartTime());
        resp.setEndTime(r.getEndTime());
        resp.setHoursCharged(r.getHoursCharged());
        resp.setRatePerHour(r.getRatePerHour());
        resp.setTotalAmount(r.getTotalAmount());
        resp.setStatus(r.getStatus());
        return resp;
    }
}
