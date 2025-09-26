package com.parking_slot.Parking_Slot_valcare.service;

import java.util.List;

import com.parking_slot.Parking_Slot_valcare.dto.ReservationRequest;
import com.parking_slot.Parking_Slot_valcare.dto.ReservationResponse;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest req);
    ReservationResponse getReservation(Long id);
    void cancelReservation(Long id);
    List<ReservationResponse> listReservations();
}
