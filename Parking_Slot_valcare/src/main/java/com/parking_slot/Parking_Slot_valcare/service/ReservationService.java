package com.parking_slot_reservation.service;

import java.util.List;

import com.parking_slot_reservation.dto.ReservationRequest;
import com.parking_slot_reservation.dto.ReservationResponse;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest req);
    ReservationResponse getReservation(Long id);
    void cancelReservation(Long id);
    List<ReservationResponse> listReservations();
}
